package com.huanghe.alarm.api.influxdb;


import com.huanghe.alarm.api.common.CacheConstant;
import com.huanghe.alarm.api.mapper.huanghe.AlarmHistoricalMapper;
import com.huanghe.alarm.api.mapper.huanghe.AlarmbasicMapper;
import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.model.AlarmHistorical;
import com.huanghe.alarm.api.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
@Transactional
public class InfluxdbScheduler {

    private final Logger logger = LoggerFactory.getLogger(InfluxdbScheduler.class);

    @Autowired
    private AlarmbasicMapper alarmbasicMapper;

    @Autowired
    private AlarmHistoricalMapper alarmHistoricalMapper;


    //@Scheduled(cron = "0 30 06 ? * *")
    public void getDataFromInfluxdb(String ipAddr, String dbName) {
        List<String> ids = alarmbasicMapper.queryAllId();

        int orderCount = (ids.size() / 1000) == 0 ? ids.size() / 1000 : (ids.size() / 1000) + 1;

        //创建指定数量的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(orderCount);

        for (int i = 0; i < orderCount; i++) {
            List<String> orderList = orderList(i, ids);
            final int index = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    //System.out.println(orderList.size()+"=====task start: " + index);
                    //logger.info(orderList.size() + "=====task start: " + index);
                    try {
                        Map<String, String[]> influxdbDataMap = SelectDataFromInfluxdb.getData(orderList, ipAddr, dbName);

                        List<AlarmDefinition> alarmDefinitionList = setDataToPojo(influxdbDataMap);
                        //将状态存入基础表
                        if (alarmDefinitionList == null || alarmDefinitionList.size() == 0) {
                            return;
                        }
                        alarmbasicMapper.batchUpdateByOrgId(alarmDefinitionList);
                        //Thread.sleep(Long.MAX_VALUE);
                        insertAlarmHistory(influxdbDataMap);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //System.out.println(orderList.size()+"========task end: " + index);
                    //logger.info(orderList.size() + "========task end: " + index);
                }

            };

            executorService.execute(runnable);
        }

    }

    //将大的list拆分成小集合
    List<String> orderList(int coderNum, List<String> ids) {
        List<String> orderList = new ArrayList<>();
        int startIndex = coderNum * 1000;
        int endIndex = (coderNum * 1000 + 1000) - 1;

        if (endIndex > ids.size()) {
            endIndex = ids.size() - 1;
        }

        for (int i = startIndex; i <= endIndex; i++) {
            String id = ids.get(i);
            orderList.add(id);
        }
        return orderList;
    }

    //将数据封装到实体类
    List<AlarmDefinition> setDataToPojo(Map<String, String[]> influxdbDataMap) {

        //创建集合用来封装实体类
        List<AlarmDefinition> alarmDefinitionList = new ArrayList<>();
        if (influxdbDataMap == null) {
            return alarmDefinitionList;
        }
        //map集合用来记录动作发生的时间间隔
        Map<String, Long> signMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : influxdbDataMap.entrySet()) {
            AlarmDefinition alarmDefinition1 = alarmbasicMapper.selectDataById(entry.getKey());

            AlarmHistorical alarmHistorical = alarmHistoricalMapper.selectPreData(entry.getKey());

            long timeDiff = 0;
            Boolean isAct = false;
            if (alarmHistorical != null) {
                //库中最新时间
                Date preTime = alarmHistorical.getActChTime();
                Date nowTime = DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]);
                timeDiff = nowTime.getTime() - preTime.getTime();

                //如果该点在历史库中的最新记录是"动作",并且距离本次查询结果的时间已经超过5s了,则该点将要被纳入动作状态了。
                if (alarmHistorical.getAlarmActive().equals(CacheConstant.ACTION) && timeDiff > 5000) {
                    isAct = true;
                }
            }

            AlarmDefinition alarmDefinition = new AlarmDefinition();
            alarmDefinition.setOrgId(entry.getKey());
            if (0 == Double.parseDouble(entry.getValue()[2])) {
                alarmDefinition.setPointStatus(CacheConstant.RESET);
                alarmDefinition.setAlarmName(alarmDefinition1.getDescript() + CacheConstant.RESET);
                alarmDefinition.setModifyTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                alarmDefinitionList.add(alarmDefinition);
            } else if (1 == Double.parseDouble(entry.getValue()[2]) && isAct) {
                alarmDefinition.setPointStatus(CacheConstant.ACTION);
                alarmDefinition.setAlarmName(alarmDefinition1.getDescript() + CacheConstant.ACTION);
                alarmDefinition.setModifyTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                alarmDefinitionList.add(alarmDefinition);
            }

        }
        return alarmDefinitionList;
    }


    /**
     * 插入历史函数
     *
     * @param map influxdb获取的数据
     */
    private void insertAlarmHistory(Map<String, String[]> map) {

        List<AlarmHistorical> histories = new ArrayList<>();

        //查询每个点下最新的历史记录
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String id = entry.getKey();


            //查基础表
            AlarmDefinition basic = alarmbasicMapper.selectBasicById(id);

            AlarmHistorical history = new AlarmHistorical();

            AlarmHistorical oldHistory = alarmHistoricalMapper.selectPreData(id);

            history.setOrgId(id);
            history.setAlarmCode(basic.getAlarmCode());
            history.setDeviceName(basic.getDeviceName());
            history.setPlantName(basic.getPlantName());
            history.setSignType(basic.getSignalType());
            history.setAlarmClass(basic.getAlarmClass());

            double num = Double.parseDouble(entry.getValue()[2]);
            String code = num == 0 ? "复位" : num == 1 ? "动作" : "";

            if ("".equals(code)) {
                continue;
            }

            //如果历史纪录为空
            if (null == oldHistory) {
                history.setAlarmName(basic.getDescript() + code);
                history.setActChTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                history.setAlarmActive(code);   //动作，复位
                histories.add(history);
            } else {
                String status = oldHistory.getAlarmActive();
                //状态有变化，则插入插入
                if (!status.equals(code)) {
                    history.setAlarmName(basic.getDescript() + code);
                    history.setActChTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                    history.setAlarmActive(code);   //动作，复位
                    histories.add(history);
                }
            }

        }

        //批量插入历史纪录
        if (histories.size() > 0) {
            alarmHistoricalMapper.batchInsertHistory(histories);
        }
    }
}
