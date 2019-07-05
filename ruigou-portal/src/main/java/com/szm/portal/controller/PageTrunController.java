package com.szm.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 */
@Controller
public class PageTrunController {

    @RequestMapping("/main")
    public String toMain(){
        System.out.println("to main..");
        return "index";
    }
}
