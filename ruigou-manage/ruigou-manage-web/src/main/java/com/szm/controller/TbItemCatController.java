package com.szm.controller;

import com.szm.pojo.EUTreeNode;
import com.szm.service.ITbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/item/cat")
public class TbItemCatController {

    @Autowired
    private ITbItemCatService service;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> list=service.getItemCatList(parentId);
        System.out.println(list.toString());
        return list;
    }
}
