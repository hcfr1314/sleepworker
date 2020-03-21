package com.huanghe.alarm.api.service;

import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.model.AlarmHistorical;

import java.util.List;

public interface AlarmSelectService {

    /**
     * ������ѯ
     *
     * @param alarmHistorical
     * @return
     */
    List<AlarmHistorical> getAlarmHistoricalByCondition(AlarmHistorical alarmHistorical);


}
