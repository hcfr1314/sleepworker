package com.hhgs.kks.controller;

import com.hhgs.kks.pojo.User;

import net.minidev.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class UserController {
    /**
     * 5      * Logger for this class
     * 6
     */
    private static final Logger logger = Logger.getLogger(String.valueOf(UserController.class));


    /*@RequestMapping("/user")
    public void test(List<User> users) {
        String jsonString = JSONArray.toJSONString(users);
        System.out.println(jsonString);

    }*/
}
