package com.szm.rest.controller;

import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContent;
import com.szm.rest.service.IContentService;
import com.szm.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * rest 内容controller
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    /**content service*/
    @Autowired
    private IContentService service;

    /**
     * 获取内容列表
     * @param contentCategoryId
     * @return 请求结果
     */
    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public RuigouResult getContentList(@PathVariable long contentCategoryId){

        try{
            return service.getContentList(contentCategoryId);
        }catch (Exception e){
            //发生错误，则返回不成功的result
            return RuigouResult.build(500, ExceptionUtil.getStackTrave(e));
        }
    }
}
