package com.szm.search.service;

import com.szm.search.pojo.SearchResult;

public interface IItemSearchService {
    SearchResult searchItem(String queryString,Integer page,Integer pageSize) throws Exception;
}
