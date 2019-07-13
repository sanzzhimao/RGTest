package com.szm.mapper;


import com.szm.pojo.User;

import java.util.List;

public interface UserMapper {
    int addUser(User user);

    User queryUserById(int id);
}
