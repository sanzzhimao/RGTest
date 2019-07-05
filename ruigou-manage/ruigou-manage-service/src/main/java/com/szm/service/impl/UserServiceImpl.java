package com.szm.service.impl;

import com.szm.mapper.TbItemMapper;
import com.szm.mapper.UserMapper;
import com.szm.pojo.TbItem;
import com.szm.pojo.User;
import com.szm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public TbItem findItem(Long id) {
        return tbItemMapper.selectByPrimaryKey(id);
    }
}
