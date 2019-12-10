package com.hhgs.kks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/bcsadddata")
    public String bcsadddata() {
        return "bcsadddata";
    }

    @RequestMapping("/bcsupdatedata")
    public String bcsupdatedata() {
        return "bcsupdatedata";
    }

    @RequestMapping("/usertest")
    public String usertest() {
        return "usertest";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping("/download")
    public String download() {
        return "download";
    }


}
