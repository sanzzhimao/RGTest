package com.szm.service;

import com.szm.pojo.ItemCatTreeNode;

import java.util.List;

public interface ITbItemCatService {
    List<ItemCatTreeNode> getItemCatList(long parentId);
}
