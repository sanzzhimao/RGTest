package com.szm.controller;

import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.TbItem;
import com.szm.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemController {
    /**
     * 注入itemService
     */
    @Autowired
    private IItemService itemService;

    /**
     * 根据id查询单个商品
     * @param itemId
     * @return
     */
    @RequestMapping("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem item=itemService.getItemById(itemId);
        return item;
    }

    /**
     * 分页查询商品列表
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDateGrid getItemList(int page, int rows){
        EasyUIDateGrid result=itemService.getItemList(page, rows);
        return result;
    }
}
