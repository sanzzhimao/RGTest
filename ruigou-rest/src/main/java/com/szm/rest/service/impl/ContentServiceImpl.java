package com.szm.rest.service.impl;

import com.szm.mapper.TbContentMapper;
import com.szm.pojo.TbContent;
import com.szm.pojo.TbContentExample;
import com.szm.rest.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper mapper;

    @Override
    public List<TbContent> getContentList(long contentCid) {
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        return mapper.selectByExample(example);

    }
}
