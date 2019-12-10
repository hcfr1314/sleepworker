package com.hhgs.kks.controller;


import com.alibaba.fastjson.JSON;
import com.hhgs.kks.common.BaseResponse;
import com.hhgs.kks.pojo.Message;
import com.hhgs.kks.pojo.User;
import com.hhgs.kks.pojo.UserModel;

import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class TestController {

    @RequestMapping("/indextest")
    public String index() {
        return "index";
    }



    @RequestMapping("/kksrequest")
    public String kksrequest() {
        return "kksrequest";
    }

    @RequestMapping(value = "/user", consumes = "apllication/json", headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public BaseResponse test(UserModel userModel) {



        try {
            List<User> users = userModel.getUsers();
            for (User user : users) {
                System.out.println(user.getPassword().toString());
            }
           // System.out.println(users);
            //System.out.println(userModel);
            return BaseResponse.initSuccessBaseResponse("成功");
        } catch (Exception e) {

            System.out.println(e);
            return BaseResponse.initErrorBaseResponse("失败");
        }

    }


    @RequestMapping(value = "/usertest", consumes = "apllication/json;charset=UTF-8", headers = "content-type=application/x-www-form-urlencoded")
    @ResponseBody
    public Object test(UserModel userModel, Model model) throws InterruptedException {

        Message message = new Message();

        List<User> users = userModel.getUsers();
        for (User user : users) {
            System.out.println(user.getPassword().toString());
        }

        model.addAttribute("message", "成功");
        message.setStatus("成功");

        //response.setContentType("application/json;charset=utf-8");

        //return "bcsadddata";

        Thread.sleep(3000);

        Object o = JSON.toJSON(message);

        String s = JSON.toJSONString(message);
        return o;
        //return "usertest";
    }
}
