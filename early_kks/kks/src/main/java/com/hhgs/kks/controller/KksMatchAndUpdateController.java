package com.hhgs.kks.controller;

import com.hhgs.kks.common.BaseResponse;
import com.hhgs.kks.pojo.FilePath;
import com.hhgs.kks.service.INaRiDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/kks/KksMatchAndUpdateController")
public class KksMatchAndUpdateController {

    @Autowired
    @Qualifier("naRiDataService")
    INaRiDataService naRiDataService;

    @ApiOperation(value = "更新测点数据",notes = "更新测点信息")
    @RequestMapping(value = "/updateKKSDataTest",method = RequestMethod.POST,consumes = "apllication/json", headers = "content-type=application/x-www-form-urlencoded;charset=UTF-8")
    public BaseResponse match(    @ApiParam(required = true,name = "filePath",value = "输入源文件路径和目标文件路径")
                                 @ModelAttribute FilePath filePath) {
        String inputPath = filePath.getInputPath();
        String outputPath = filePath.getOutputPath();

        Boolean aBoolean = naRiDataService.updateData(inputPath, outputPath);
        if (aBoolean) {
            return BaseResponse.initSuccessBaseResponse("文件输出成功");
        }else {
            return BaseResponse.initErrorBaseResponse("文件输出失败");
        }

    }

    @ApiOperation(value = "更新测点数据",notes = "更新测点信息")
    @RequestMapping(value = "/updateKKSData",method = RequestMethod.POST)
    public String matchtest(@ApiParam(required = true,name = "filePath",value = "输入源文件路径和目标文件路径")
                                   FilePath filePath, Model model) {
        String inputPath = filePath.getInputPath();
        String outputPath = filePath.getOutputPath();
        ModelAndView modelAndView = new ModelAndView("bcsupdatedata");
        Boolean aBoolean = naRiDataService.updateData(inputPath, outputPath);
        if (aBoolean) {
            model.addAttribute("message","成功");
        }else {
            model.addAttribute("message","失败");

        }
        return "bcsupdatedata";

    }



}
