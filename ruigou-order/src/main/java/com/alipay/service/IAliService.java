package com.alipay.service;

import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbOrder;

public interface IAliService {
    TbOrder selectById(String id);

    RuigouResult updateById(String id);
}
