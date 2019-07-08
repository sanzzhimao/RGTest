package com.szm.controller;

import com.szm.pojo.EUTreeNode;
import com.szm.pojo.RuigouResult;
import com.szm.service.IContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private IContentCategoryService service;

    /**
     * 查询所有当前（第一级或点击之后这一级的内容分类）
     * @param parentId 父节点id
     * @return 子节点集合
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCat(@RequestParam(value = "id",defaultValue = "0")long parentId){
        return service.getContentCat(parentId);
    }

    /**
     * 添加内容分类节点
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public RuigouResult createContentCategory(Long parentId, String name){
        RuigouResult result=service.insertContentCat(parentId, name);
        return result;
    }
}
