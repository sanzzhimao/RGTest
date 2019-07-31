package com.szm.service;

import com.szm.pojo.EUTreeNode;
import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContentCategory;

import java.util.List;

public interface IContentCategoryService {

    List<EUTreeNode> getContentCat(long parentId);

    RuigouResult insertContentCat(long parentId, String name);

    RuigouResult updateContentCat(long id,String name);

    RuigouResult delectContentCat(long id);
}
