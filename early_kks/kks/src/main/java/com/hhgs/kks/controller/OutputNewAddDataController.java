package com.hhgs.kks.controller;


import com.alibaba.fastjson.JSON;
import com.hhgs.kks.common.BaseResponse;
import com.hhgs.kks.pojo.FileMessage;
import com.hhgs.kks.pojo.FilePath;
import com.hhgs.kks.pojo.Message;
import com.hhgs.kks.pojo.SourceFile;
import com.hhgs.kks.service.INaRiDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OutputNewAddDataController {

    @Autowired
    @Qualifier("naRiDataService")
    INaRiDataService naRiDataService;

    @ApiOperation(value = "获取nari新增加的数据",notes = "获取nari新增加的数据")
    @RequestMapping(value = "/updateKKSDataTest",method = RequestMethod.POST)
    public BaseResponse matchTest(@ApiParam(required = true,name = "filePath",value = "输入源文件路径和目标文件路径")
                              @RequestBody FileMessage fileMessage) {

        List<String> sourcePathList = new ArrayList<>();
        List<SourceFile> inputPathList = fileMessage.getInputPathList();
        for (SourceFile sourceFile : inputPathList) {
            sourcePathList.add(sourceFile.getPath());
        }
        String outputPath = fileMessage.getOutputPath();
        String scheduler_name = fileMessage.getScheduler_name();

        Boolean aBoolean = naRiDataService.outputNewAddData(sourcePathList,outputPath,scheduler_name);
        if (aBoolean) {
            return BaseResponse.initSuccessBaseResponse("文件输出成功");
        }else {
            return BaseResponse.initErrorBaseResponse("文件输出失败");
        }

    }

    @ApiOperation(value = "获取nari新增加的数据",notes = "获取nari新增加的数据")
    //@ResponseBody
    @RequestMapping(value = "/updateKKSData",method = RequestMethod.POST)
    public String match(@ApiParam(required = true,name = "filePath",value = "输入源文件路径和目标文件路径")
                             FileMessage fileMessage, Model model) {


        List<String> sourcePathList = new ArrayList<>();


        List<SourceFile> inputPathList = fileMessage.getInputPathList();

        if(inputPathList.get(0).getPath()==null|| inputPathList.get(0).getPath()=="") {
            model.addAttribute("message","请将参数输入完整");
            return "bcsadddata";

        }
        for (SourceFile sourceFile : inputPathList) {
            sourcePathList.add(sourceFile.getPath());
        }

        String outputPath = fileMessage.getOutputPath();
        String scheduler_name = fileMessage.getScheduler_name();

        Message message = new Message();

        Boolean aBoolean = naRiDataService.outputNewAddData(sourcePathList,outputPath,scheduler_name);
        if (aBoolean) {
            //message.setStatus("文件输出成功");
            model.addAttribute("message","文件输出成功");
        }else {
            model.addAttribute("message","文件输出失败");
            //message.setStatus("文件输出失败");
        }

        /*Object o = JSON.toJSON(message);
        return o;*/

        return "bcsadddata";

    }


}