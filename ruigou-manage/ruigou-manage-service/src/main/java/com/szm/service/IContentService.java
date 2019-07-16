package com.szm.service;

import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContent;

public interface IContentService {

    EasyUIDateGrid getContentList(int page, int rows,long categoryId);

    RuigouResult addContent(TbContent content);
}
