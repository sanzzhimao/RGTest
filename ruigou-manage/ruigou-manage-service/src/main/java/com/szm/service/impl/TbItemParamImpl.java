package com.szm.service.impl;

import com.szm.mapper.TbItemMapper;
import com.szm.mapper.TbItemParamMapper;
import com.szm.pojo.TbItemParam;

import com.szm.pojo.TbItemParamExample;
import com.szm.service.ITbItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbItemParamImpl implements ITbItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

    @Override
    public TbItemParam findTbItemParam(Long id) {
        TbItemParamExample example=new TbItemParamExample();
        TbItemParamExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItemParam> list=tbItemParamMapper.selectByExample(example);
        if (list!=null && list.size()>0){
            TbItemParam tbItemParam=list.get(0);
            return tbItemParam;
        }
        return null;
    }
}
