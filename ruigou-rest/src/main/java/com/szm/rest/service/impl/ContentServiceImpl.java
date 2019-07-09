package com.szm.rest.service.impl;

import com.szm.search.mapper.TbContentMapper;
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

    /**
     * 获取(对应位置-类目id下所有内容)内容列表
     * @param contentCid
     * @return
     */
    @Override
    public List<TbContent> getContentList(long contentCid) {
        TbContentExample example=new TbContentExample();
        TbContentExample.Criteria criteria=example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        return mapper.selectByExample(example);

    }
}
