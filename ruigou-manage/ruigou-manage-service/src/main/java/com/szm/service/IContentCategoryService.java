package com.szm.service;

import com.szm.pojo.EUTreeNode;
import com.szm.pojo.RuigouResult;

import java.util.List;

public interface IContentCategoryService {

    List<EUTreeNode> getContentCat(long parentId);

    RuigouResult insertContentCat(long parentId, String name);
}
