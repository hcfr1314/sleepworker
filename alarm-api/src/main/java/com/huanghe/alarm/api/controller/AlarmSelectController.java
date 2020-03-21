package com.huanghe.alarm.api.controller;


import com.huanghe.alarm.api.common.PageUtil;
import com.huanghe.alarm.api.model.AlarmHistorical;
import com.huanghe.alarm.api.service.AlarmSelectService;
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
@RequestMapping("/alarmSelect")
public class AlarmSelectController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CurrentAlarmController.class);

    @Autowired
    private AlarmSelectService alarmSelectService;

    @ApiOperation(value = "告警查询（支持条件查询）", notes = "告警查询（支持条件查询）")
    @PostMapping("getAlarmHistorical")
    public BaseResponse getAlarmHistorical(@ApiParam(required = true, value = "pageSize") @RequestParam(required = true, defaultValue = "10") int pageSize,
                                           @ApiParam(required = true, value = "pageNum") @RequestParam(required = true, defaultValue = "1") int pageNum,
                                           @ApiParam(required = true, value = "alarmDefinition") @RequestBody(required = true) AlarmHistorical alarmHistorical) {

        try {
            PageUtil.processPageNumberAndSize(pageNum,pageSize);
            List<AlarmHistorical> alarmHistoricalList = alarmSelectService.getAlarmHistoricalByCondition(alarmHistorical);
            return processPaging(alarmHistoricalList,pageNum,pageSize);
        } catch (Exception e) {
            logger.error("AlarmSelectController--getAlarmHistorical--e"+e.getMessage());
            e.printStackTrace();
            return BaseResponse.initError(null,e.getMessage());
        }
    }
}
