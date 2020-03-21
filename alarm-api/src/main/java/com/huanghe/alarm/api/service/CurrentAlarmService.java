package com.huanghe.alarm.api.service;

import com.huanghe.alarm.api.model.AlarmDefinition;

import java.util.List;

public interface CurrentAlarmService {


    /**
     * 查询场站告警信息
     * @param alarmDefinition
     * @return
     */
    List<AlarmDefinition> selectByCondition(AlarmDefinition alarmDefinition);

    List<AlarmDefinition> queryByCondition(AlarmDefinition alarmDefinition);

    void addAlarm(AlarmDefinition alarmDefinition);

    int editAlarm(AlarmDefinition alarmDefinition);

    int delAlarm(List<String> ids);

    List<String> getAllPlantName();
}
