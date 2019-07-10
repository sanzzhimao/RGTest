package com.szm.search.controller;

import com.szm.pojo.RuigouResult;
import com.szm.search.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manage")
public class ItemController {
    @Autowired
    private IItemService service;

    /**
     * 导入商品到solr索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public RuigouResult importAll(){
        System.out.println("importall..");
        RuigouResult result=service.importAllItems();
        return result;

    }
}
