package com.huanghe.alarm.api.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.huanghe.alarm.api.common.Pages;
import com.huanghe.alarm.api.model.AlarmDefinition;
import com.huanghe.alarm.api.service.CurrentAlarmService;
import com.huanghe.alarm.api.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@Api("告警设置公共接口")
@RequestMapping("/setting")
public class AlarmSettingcontroller extends BaseController {

    @Autowired
    private CurrentAlarmService currentService;

    @PostMapping("/query")
    @ApiOperation(value = "根据告警类型，场站名称，告警名称，告警等级，有效性，业务类别过滤告警信息")
    public BaseResponse queryByCondition(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                         @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum,
                                         @ApiParam(required = true, value = "alarmDefinition") @RequestBody(required = true) AlarmDefinition alarmDefinition
    ) {
        List<AlarmDefinition> list = currentService.queryByCondition(alarmDefinition);
        return processPaging(list, pageNum, pageSize);
    }


    @PostMapping("/add")
    @ApiOperation(value = "新增告警")
    public BaseResponse addAlarm(@ApiParam(required = true, value = "alarmDefinition") @RequestBody(required = true) AlarmDefinition alarmDefinition) {
        currentService.addAlarm(alarmDefinition);
        return BaseResponse.initSuccess(null, null);
    }


    @PostMapping("/edit")
    @ApiOperation(value = "编辑告警")
    public BaseResponse editAlarm(@ApiParam(required = true, value = "alarmDefinition") @RequestBody(required = true) AlarmDefinition alarmDefinition) {
        int i = currentService.editAlarm(alarmDefinition);
        if (i == -1) {
            return BaseResponse.initError("没有原数据，不能修改", null);
        } else if (i == 1) {
            return BaseResponse.initSuccess("更新成功", null);
        } else {
            return BaseResponse.initError("更新失败", null);
        }
    }

    @PostMapping("/del")
    @ApiOperation(value = "删除告警")
    public BaseResponse delAlarm(@ApiParam(required = true) @RequestBody List<String> ids) {
        int i = currentService.delAlarm(ids);
        if (i != 0) {
            return BaseResponse.initSuccess("删除成功", null);
        } else {
            return BaseResponse.initError("删除失败", null);
        }
    }


}
