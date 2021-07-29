package com.example.hong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping("/user/add")
    public String toAdd(){
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String toUdp(){
        return "user/update";
    }
}
