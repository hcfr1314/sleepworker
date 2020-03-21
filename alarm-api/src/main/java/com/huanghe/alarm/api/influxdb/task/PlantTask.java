package com.huanghe.alarm.api.influxdb.task;

import com.huanghe.alarm.api.influxdb.InfluxdbScheduler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PlantTask extends InfluxdbScheduler {

    @Value("${chakaAddr}")
    private String chakaAddr;

    @Value("${chakaDbName}")
    private String chaka;

    //@Scheduled(fixedRate = 180000)
    public void chakaTask() {
        getDataFromInfluxdb(chakaAddr, chaka);
    }
}
