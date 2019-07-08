package com.szm.service.impl;

import com.szm.pojo.EUTreeNode;
import com.szm.mapper.TbItemCatMapper;
import com.szm.pojo.TbItemCat;
import com.szm.pojo.TbItemCatExample;
import com.szm.service.ITbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements ITbItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EUTreeNode> getItemCatList(long parentId) {
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats=tbItemCatMapper.selectByExample(example);
        List<EUTreeNode> list=new ArrayList<>();
        for (TbItemCat itemCat:itemCats){
            EUTreeNode catTreeNode=new EUTreeNode();
            catTreeNode.setId(itemCat.getId());
            catTreeNode.setText(itemCat.getName());
            catTreeNode.setState(itemCat.getIsParent()?"closed":"open");
            list.add(catTreeNode);
        }
        return list;
    }
}
