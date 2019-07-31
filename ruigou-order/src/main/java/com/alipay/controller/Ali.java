package com.alipay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Ali {
    @RequestMapping("/ali")
    public String ali(){
        return "index";
    }
}
