package com.szm.service.impl;

import com.szm.mapper.TbItemMapper;
import com.szm.mapper.TbOrderMapper;
import com.szm.mapper.TbUserMapper;
import com.szm.pojo.TbItemExample;
import com.szm.pojo.TbOrderExample;
import com.szm.pojo.TbUserExample;
import com.szm.service.IInformationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;

public class InformationStatisticsImpl implements IInformationStatisticsService {

    @Autowired
    TbItemMapper tbItemMapper;
    @Autowired
    TbUserMapper tbUserMapper;
    @Autowired
    TbOrderMapper tbOrderMapper;

    @Override
    public Integer getOrdersQuantity() {
        TbOrderExample Example=new TbOrderExample();
        //设置查询条件，这里不设置
//        TbOrderExample.Criteria criteria=Example.createCriteria();
//        criteria.andOrderIdEqualTo("");
        int count=tbOrderMapper.countByExample(Example);
        return count;
    }

    @Override
    public Integer getItemsQuantity() {
        TbItemExample Example=new TbItemExample();
        int count=tbItemMapper.countByExample(Example);
        return count;
    }

    @Override
    public Integer getUsersQuantity() {
        TbUserExample Example=new TbUserExample();
        int count=tbUserMapper.countByExample(Example);
        return count;
    }
}
