package com.szm.service.impl;


import com.szm.mapper.TbItemDescMapper;
import com.szm.pojo.TbItemDesc;
import com.szm.pojo.TbItemDescExample;
import com.szm.service.ItemInquiriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemInquiriesServiceImpl implements ItemInquiriesService {
    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Override
    public TbItemDesc getItemDescById(long itemId) {
        TbItemDescExample example=new TbItemDescExample();
        TbItemDescExample.Criteria criteria=example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> list=itemDescMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            TbItemDesc result=list.get(0);
            return result;
        }
        return null;
    }

    @Override
    public void removeItemDescById(long itemId) {
        itemDescMapper.deleteByPrimaryKey(itemId);
    }

    @Override
    public void modifyItemDescByItem(TbItemDesc itemDesc) {
        itemDescMapper.updateByPrimaryKey(itemDesc);
    }
}
