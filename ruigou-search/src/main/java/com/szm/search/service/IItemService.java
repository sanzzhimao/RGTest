package com.szm.search.service;

import com.szm.pojo.RuigouResult;
import com.szm.search.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;

public interface IItemService {

    RuigouResult importAllItems();
}
