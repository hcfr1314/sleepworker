package com.huanghe.alarm.api.controller;

import com.huanghe.alarm.api.common.PageUtil;
import com.huanghe.alarm.api.mapper.huanghe.AlarmbasicMapper;
import com.huanghe.alarm.api.service.AlarmSelectService;
import com.huanghe.alarm.api.utils.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/test")
@RestController
@CrossOrigin
@Api(value="测试接口",tags = "测试",description = "测试接口")
public class TestController extends BaseController {

    @Autowired
    private AlarmSelectService service;

    //@Autowired
    //private AlarmHistoricalMapper yxMapper;

    @Autowired
    private AlarmbasicMapper alarmbasicMapper;

    @GetMapping("/do")
    @ApiOperation(value="测试接口",httpMethod = "GET",notes = "测试配置多数据源")
    public BaseResponse doSomething(){
        //return service.test();
       List<Integer> result=new ArrayList<>();
        /*int i = yxMapper.queryCount();*/
        PageUtil.processPageNumberAndSize(1,15);
        List<String> strings = alarmbasicMapper.queryAllId();
        //result.add(i);
        //result.add(j);

        System.out.println(strings);
        //System.out.println(i+"+++"+j);
        return  processPaging(strings,1,15);

    }


}
