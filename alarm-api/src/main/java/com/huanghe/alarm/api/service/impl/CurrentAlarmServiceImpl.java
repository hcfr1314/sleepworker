package com.huanghe.alarm.api.service.impl;

import com.huanghe.alarm.api.common.CacheConstant;
import com.huanghe.alarm.api.mapper.huanghe.AlarmbasicMapper;
import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.service.CurrentAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Service
public class CurrentAlarmServiceImpl implements CurrentAlarmService {

    @Autowired
    private AlarmbasicMapper alarmbasicMapper;


    @Override
    public List<AlarmDefinition> selectByCondition(AlarmDefinition alarmDefinition) {
        //只查询发生动作的点
        return alarmbasicMapper.selectByCondiction(alarmDefinition,CacheConstant.ACTION);
    }

    @Override
    public List<AlarmDefinition> queryByCondition(AlarmDefinition alarmDefinition) {
        List<AlarmDefinition> list = alarmbasicMapper.queryByCondition(alarmDefinition);
        return list;
    }

    @Override
    @Transactional
    public void addAlarm(AlarmDefinition alarmDefinition) {
        alarmDefinition.setCreateTime(new Date());
        alarmbasicMapper.addAlarm(alarmDefinition);
    }

    @Override
    @Transactional
    public int editAlarm(AlarmDefinition alarmDefinition) {
        AlarmDefinition entity = alarmbasicMapper.selectBasicById(alarmDefinition.getOrgId());
        if (entity == null) {
            return -1;
        }
        alarmDefinition.setModifyTime(new Date());
        int code = alarmbasicMapper.editAlarm(alarmDefinition);
        return code;
    }

    @Override
    @Transactional
    public int delAlarm(List<String> ids) {
        int result = alarmbasicMapper.delAlarm(ids);
        return result;
    }

    @Override
    public List<String> getAllPlantName() {
        return alarmbasicMapper.getAllPantName();
    }
}
