package com.huanghe.alarm.api.influxdb;

import com.huanghe.alarm.api.utils.DateUtil;
import com.huanghe.alarm.api.utils.InfluxDBConnection;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.stream.Collectors;


public class SelectDataFromInfluxdb {

    private static Logger logger  = LoggerFactory.getLogger(SelectDataFromInfluxdb.class);

    //SELECT * FROM data where id = '313633676194' or id = '313633676198'  and time >='2019-11-07T00:00:00.000Z' and time <= '2019-11-08T07:00:00.000Z' order by time desc limit 1000 tz('Asia/Shanghai')
    public static Map<String, String[]> getData(List<String> ids,String ipddr,String dbName) {

        InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "admin", ipddr, dbName, "hour");
        //InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "admin", "http://172.17.86.20:8086", "chaka", null);
        StringBuilder sb1 = new StringBuilder();
        sb1.append("SELECT * FROM data where (");
        //sb1.append("SELECT * FROM chaka where (");
        for (String id : ids) {
            sb1.append(" id = '"+id+"' or " );
        }
        int index = sb1.lastIndexOf("or");
        sb1.delete(index,sb1.length());
        sb1.append(") ");
        long curremtTime = DateUtil.getCurrentTime();
        long freTime = curremtTime-180000;

        sb1.append("and time >= "+freTime+"ms "+"and time <= "+curremtTime+"ms"+" order by time asc limit 3000 tz('Asia/Shanghai')");

        QueryResult results = influxDBConnection
                .query(sb1.toString());
        //results.getResults()是同时查询多条SQL语句的返回值，此处我们只有一条SQL，所以只取第一个结果集即可。
        Map<String, String[]> resultMap = null;
        QueryResult.Result oneResult = results.getResults().get(0);
        if (oneResult.getSeries() != null) {
            List<List<Object>> valueList = oneResult.getSeries().stream().map(QueryResult.Series::getValues)
                    .collect(Collectors.toList()).get(0);
            resultMap = new HashMap<>();
            if (valueList != null && valueList.size() > 0) {
                for (List<Object> value : valueList) {
                    //  数据库中字段1取值(时间戳)
                    String field1 = value.get(0) == null ? null : value.get(0).toString();
                    //  数据库中字段2取值(原始测点)
                    String field2 = value.get(1) == null ? null : value.get(1).toString();
                    //  数据质量
                    String field3 = value.get(2) == null ? null : value.get(2).toString();
                    //  实时值
                    String field4 = value.get(3) == null ? null : value.get(3).toString();

                    //将查询结果放入数组
                    String[] arr = {field2, field3, field4, field1};
                    //
                    resultMap.put(field2, arr);

                    //System.out.println(field1+":"+field2+":"+field3+":"+field4);
                    //System.out.println(field1+"====="+field2+"====="+field3+"====="+field4);
                    // TODO 用取出的字段做你自己的业务逻辑……
                }
            }

           /* Set<Map.Entry<String, String[]>> entrySet = resultMap.entrySet();

            for (Map.Entry<String, String[]> stringEntry : entrySet) {
                //System.out.println(stringEntry.getKey() + "=========" + stringEntry.getValue()[3] + "=========" + stringEntry.getValue()[2]);
                logger.info(stringEntry.getKey() + "=========" + stringEntry.getValue()[3] + "=========" + stringEntry.getValue()[2]);
            }*/
        }
        //关闭连接
        influxDBConnection.close();

        return resultMap;
    }


    public static void main(String[] args) {
        try {
            System.out.println(DateUtil.getCurrentTime());
            String[] idsAyyay = {"317928519120","317928519030","317928518850","317928518290"};
            List<String> strings = Arrays.asList(idsAyyay);
            getData(strings,"http://172.28.8.10:8086","chaka");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
