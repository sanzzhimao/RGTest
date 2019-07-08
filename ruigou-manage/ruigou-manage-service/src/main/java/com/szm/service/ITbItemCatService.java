package com.szm.service;

import com.szm.pojo.EUTreeNode;

import java.util.List;

public interface ITbItemCatService {
    List<EUTreeNode> getItemCatList(long parentId);
}
