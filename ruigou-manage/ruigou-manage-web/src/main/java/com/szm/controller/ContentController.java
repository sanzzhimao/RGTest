package com.szm.controller;

import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContent;
import com.szm.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private IContentService service;

    @RequestMapping("/query/list")
    @ResponseBody
    public EasyUIDateGrid getItemList(int page, int rows,long categoryId){
        EasyUIDateGrid result=service.getContentList(page, rows,categoryId);
        return result;
    }

    @RequestMapping("/save")
    @ResponseBody
    public RuigouResult createContent(Long categoryId, String title, String subTiltle, String titleDesc, String url, String pic, String pic2, String content){
        System.out.println("content-save");
        TbContent content1=new TbContent();
        content1.setCategoryId(categoryId);
        content1.setTitle(title);
        content1.setSubTitle(subTiltle);
        content1.setTitleDesc(titleDesc);
        content1.setUrl(url);
        content1.setPic(pic);
        content1.setPic2(pic2);
        content1.setContent(content);
        RuigouResult result=service.addContent(content1);
        return result;
    }
}
