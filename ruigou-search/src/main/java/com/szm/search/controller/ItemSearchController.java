package com.szm.search.controller;

import com.szm.pojo.RuigouResult;
import com.szm.search.pojo.SearchResult;
import com.szm.search.service.IItemSearchService;
import com.szm.util.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemSearchController {
    @Autowired
    private IItemSearchService searchService;

    @RequestMapping("/q")
    @ResponseBody
    public RuigouResult search(@RequestParam(value = "kw",defaultValue = "*.*")String queryString,
                               @RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "10")Integer pagesize){
        if (StringUtils.isBlank(queryString)) {
            return RuigouResult.build(400, "查询条件是必须的参数");
        }
        SearchResult result = null;
        try {
            queryString=new String(queryString.getBytes("ISO8859-1"),"utf-8");
            result = searchService.searchItem(queryString, page,pagesize);

        } catch (Exception e) {
            e.printStackTrace();
            return RuigouResult.build(500, ExceptionUtil.getStackTrave(e));
        }

        return RuigouResult.ok(result);
    }


}
