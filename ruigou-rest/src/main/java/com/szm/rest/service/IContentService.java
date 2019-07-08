package com.szm.rest.service;

import com.szm.pojo.TbContent;

import java.util.List;

public interface IContentService {

    List<TbContent> getContentList(long contentCid);
}
