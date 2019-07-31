package com.szm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szm.mapper.TbUserMapper;
import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.TbUser;
import com.szm.pojo.TbUserExample;
import com.szm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicelmpl implements IUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    /**
     * 查询所用用户
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDateGrid getUserList(int page, int rows) {
        TbUserExample example = new TbUserExample();

        PageHelper.startPage(page,rows);
        List<TbUser> list = tbUserMapper.selectByExample(example);
        PageInfo<TbUser> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIDateGrid result = new EasyUIDateGrid();
        result.setTotal(total);
        result.setRows(list);
        System.out.println(list);
        return result;
    }

    /**
     * 模糊查询姓名
     * @param name
     * @return
     */
    @Override
    public EasyUIDateGrid getUserNameList(int page, int rows,String name) {
        TbUserExample example = new TbUserExample();

        PageHelper.startPage(page,rows);
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameLike("%"+name+"%");

        List<TbUser> list = tbUserMapper.selectByExample(example);
        PageInfo<TbUser> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        EasyUIDateGrid result = new EasyUIDateGrid();
        result.setTotal(total);
        result.setRows(list);
        System.out.println(list);
        return result;
    }
}
