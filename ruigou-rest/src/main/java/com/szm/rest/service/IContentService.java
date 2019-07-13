package com.szm.rest.service;

import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContent;

import java.util.List;

public interface IContentService {

    RuigouResult getContentList(long contentCid);
}
