package com.szm.search.service.impl;

import com.szm.search.mapper.IItemDao;
import com.szm.search.pojo.SearchResult;
import com.szm.search.service.IItemSearchService;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSearchServiceImpl implements IItemSearchService {

    @Autowired
    private IItemDao itemDao;

    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult searchItem(String queryString, Integer page,Integer pageSize) throws Exception {
        SolrQuery query=new SolrQuery();

        if (StringUtils.isBlank(queryString)){
            //如果没有查询条件则设为查所有
            query.setQuery("*.*");
        }else {
            query.setQuery(queryString);
        }
        //分页
        if (page == null){
            page = 1;
        }
        //显示条数
        if (pageSize == null){
            pageSize = 10;
        }
        query.setStart((page-1)*pageSize);
        query.setRows(pageSize);
        //高亮显示
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        //设置默认搜索域
        query.set("df","item_keywords");
        //查询
        SearchResult result=itemDao.searchItem(query);
        //分页计算
        Long recordCount=result.getRecordCount();
        int pageCount=(int)(recordCount/pageSize);
        if (recordCount%pageSize>0){
            pageCount++;
        }
        result.setPageCount(pageCount);
        result.setCurPage(page);
        return result;
    }
}
