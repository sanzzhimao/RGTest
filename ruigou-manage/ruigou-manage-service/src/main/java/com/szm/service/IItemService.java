package com.szm.service;

import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.TbItem;

public interface IItemService {

    TbItem getItemById(Long itemId);

    EasyUIDateGrid getItemList(int page, int rows);
}
