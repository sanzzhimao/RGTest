package com.alipay.service;

import com.szm.mapper.TbOrderMapper;
import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AliService implements IAliService{
    @Autowired
    TbOrderMapper mapper;

    @Override
    public TbOrder selectById(String id) {
        TbOrder tbOrder=mapper.selectByPrimaryKey(id);
        return tbOrder;
    }
    /**
     * 支付成功回掉
     * @param id
     * @return
     */
    @Override
    public RuigouResult updateById(String id) {
        TbOrder tbOrder=new TbOrder();
        tbOrder.setOrderId(id);
        tbOrder.setPaymentType(1);
        tbOrder.setStatus(2);
        tbOrder.setUpdateTime(new Date());
        tbOrder.setPaymentTime(new Date());
        mapper.updateByPrimaryKeySelective(tbOrder);
        return RuigouResult.ok();
    }
}
