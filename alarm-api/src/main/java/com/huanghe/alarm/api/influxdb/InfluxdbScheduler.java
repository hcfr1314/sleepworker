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

        //����ָ���������̳߳�
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
                        //��״̬���������
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

    //�����list��ֳ�С����
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

    //�����ݷ�װ��ʵ����
    List<AlarmDefinition> setDataToPojo(Map<String, String[]> influxdbDataMap) {

        //��������������װʵ����
        List<AlarmDefinition> alarmDefinitionList = new ArrayList<>();
        if (influxdbDataMap == null) {
            return alarmDefinitionList;
        }
        //map����������¼����������ʱ����
        Map<String, Long> signMap = new HashMap<>();
        for (Map.Entry<String, String[]> entry : influxdbDataMap.entrySet()) {
            AlarmDefinition alarmDefinition1 = alarmbasicMapper.selectDataById(entry.getKey());

            AlarmHistorical alarmHistorical = alarmHistoricalMapper.selectPreData(entry.getKey());

            long timeDiff = 0;
            Boolean isAct = false;
            if (alarmHistorical != null) {
                //��������ʱ��
                Date preTime = alarmHistorical.getActChTime();
                Date nowTime = DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]);
                timeDiff = nowTime.getTime() - preTime.getTime();

                //����õ�����ʷ���е����¼�¼��"����",���Ҿ��뱾�β�ѯ�����ʱ���Ѿ�����5s��,��õ㽫Ҫ�����붯��״̬�ˡ�
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
     * ������ʷ����
     *
     * @param map influxdb��ȡ������
     */
    private void insertAlarmHistory(Map<String, String[]> map) {

        List<AlarmHistorical> histories = new ArrayList<>();

        //��ѯÿ���������µ���ʷ��¼
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String id = entry.getKey();


            //�������
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
            String code = num == 0 ? "��λ" : num == 1 ? "����" : "";

            if ("".equals(code)) {
                continue;
            }

            //�����ʷ��¼Ϊ��
            if (null == oldHistory) {
                history.setAlarmName(basic.getDescript() + code);
                history.setActChTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                history.setAlarmActive(code);   //��������λ
                histories.add(history);
            } else {
                String status = oldHistory.getAlarmActive();
                //״̬�б仯����������
                if (!status.equals(code)) {
                    history.setAlarmName(basic.getDescript() + code);
                    history.setActChTime(DateUtil.getDateTimeFromInfluxdb(entry.getValue()[3]));
                    history.setAlarmActive(code);   //��������λ
                    histories.add(history);
                }
            }

        }

        //����������ʷ��¼
        if (histories.size() > 0) {
            alarmHistoricalMapper.batchInsertHistory(histories);
        }
    }
}
