package com.szm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szm.mapper.TbContentMapper;
import com.szm.pojo.*;
import com.szm.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper mapper;

    @Override
    public EasyUIDateGrid getContentList(int page, int rows,long categoryId) {
        //执行查询，并实现分页
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //设置分页信息
        PageHelper.startPage(page,rows);
        List<TbContent> itemslist=mapper.selectByExample(example);
        PageInfo<TbContent> pageInfo=new PageInfo<>(itemslist);
        long total=pageInfo.getTotal();
        EasyUIDateGrid result=new EasyUIDateGrid();
        result.setTotal(total);
        result.setRows(itemslist);
        return result;
    }

    @Override
    public RuigouResult addContent(TbContent content) {

        /*private Date created;

        private Date updated;

        private String content;*/
        content.setCreated(new Date());
        content.setUpdated(new Date());
        mapper.insert(content);
        return RuigouResult.ok(content);
    }
}
