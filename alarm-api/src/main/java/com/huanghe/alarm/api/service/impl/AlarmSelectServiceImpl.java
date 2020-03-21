package com.huanghe.alarm.api.service.impl;

import com.huanghe.alarm.api.mapper.huanghe.AlarmHistoricalMapper;
import com.huanghe.alarm.api.mapper.huanghe.AlarmbasicMapper;
import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.model.AlarmHistorical;
import com.huanghe.alarm.api.service.AlarmSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AlarmSelectServiceImpl implements AlarmSelectService {

    @Autowired
    private AlarmHistoricalMapper alarmHistoricalMapper;


    @Override
    public List<AlarmHistorical> getAlarmHistoricalByCondition(AlarmHistorical alarmHistorical) {

        return alarmHistoricalMapper.getAlarmHistoricalByCondition(alarmHistorical);

    }

}
