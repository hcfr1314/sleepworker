package com.huanghe.alarm.api.controller;


import com.huanghe.alarm.api.common.PageUtil;
import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.service.CurrentAlarmService;
import com.huanghe.alarm.api.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@Api(description = "当前告警")
@RequestMapping("/currentAlarm")
public class CurrentAlarmController extends BaseController  {

    private static Logger logger = LoggerFactory.getLogger(CurrentAlarmController.class);


    @Autowired
    private CurrentAlarmService currentAlarmService;

    @ApiOperation(value = "当前告警（支持条件查询）",notes = "当前告警（支持条件查询）")
    @PostMapping("getAllCurrentAlarm")
    public BaseResponse getAllCurrentAlarm(@ApiParam(required = true,value = "pageSize") @RequestParam(required = true,defaultValue = "10") int pageSize,
                                           @ApiParam(required = true,value = "pageNum") @RequestParam(required = true,defaultValue = "1") int pageNum,
                                           @ApiParam(required = true, value = "alarmDefinition") @RequestBody(required = true)AlarmDefinition alarmDefinition) {
        try {
            PageUtil.processPageNumberAndSize(pageNum,pageSize);
            List<AlarmDefinition> alarmDefinitionList = currentAlarmService.selectByCondition(alarmDefinition);
            if(alarmDefinitionList == null) {
                return BaseResponse.initError(null,"查寻结果为空");
            }else {
                return processPaging(alarmDefinitionList,pageNum,pageSize);
            }

        } catch (Exception e) {
            logger.error("CurrentAlarmController--getAllCurrentAlarm--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }

    @ApiOperation(value = "获取电场列表",notes = "获取电场列表")
    @PostMapping("getAllPlantName")
    public BaseResponse getAllPlantName() {
        try {
            List<String> plantNameList = currentAlarmService.getAllPlantName();
            return BaseResponse.initSuccess(plantNameList,null);
        } catch (Exception e) {
            logger.error("CurrentAlarmController--getAllPlantName--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }

    }



}
