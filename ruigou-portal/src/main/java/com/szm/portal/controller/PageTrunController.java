package com.szm.portal.controller;

import com.szm.portal.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转Controller
 */
@Controller
public class PageTrunController {

    @Autowired
    private IContentService contentService;

    @RequestMapping("/main")
    public String toMain(Model model){
        System.out.println("to main..");

        String result = contentService.getContentList();
        model.addAttribute("ad1",result);

        return "index";
    }
}
