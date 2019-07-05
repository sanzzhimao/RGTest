package com.szm.controller;

import com.szm.pojo.TbItemParam;
import com.szm.service.ITbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbItemParamController {
    @Autowired
    private ITbItemParamService tbItemParamService;
    @RequestMapping("/itemparam/{itemParamId}")
    @ResponseBody
    public TbItemParam getParamById(@PathVariable Long itemParamId){
        System.out.println(itemParamId);
        TbItemParam tbItemParam=tbItemParamService.findTbItemParam(itemParamId);
        System.out.println(tbItemParam);
        return tbItemParam;
    }
}
