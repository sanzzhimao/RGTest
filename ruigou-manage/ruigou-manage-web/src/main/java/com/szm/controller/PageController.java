package com.szm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转
 */
@Controller
public class PageController {
    @RequestMapping("/")
    public String showIndex(){
        System.out.println("to index.jsp");
        return "index";
    }
}
