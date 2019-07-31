package com.szm.service;


import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.TbUser;


public interface IUserService {
    //查询用户信息
    EasyUIDateGrid getUserList(int page, int rows);
    //用户模糊查询
    EasyUIDateGrid getUserNameList(int page, int rows,String name);
}
