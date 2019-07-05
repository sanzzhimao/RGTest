package com.szm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/Hello")
    public String hello(){
        System.out.println("hello..");
        return "hello";
    }
}
