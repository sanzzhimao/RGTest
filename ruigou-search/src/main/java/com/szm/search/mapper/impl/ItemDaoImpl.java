package com.szm.search.mapper.impl;

import com.szm.search.mapper.IItemDao;
import com.szm.search.pojo.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.szm.search.pojo.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ItemDaoImpl implements IItemDao {
    @Autowired
    private SolrServer solrServer;

    @Override
    public SearchResult searchItem(SolrQuery solrQuery) throws Exception {
        //根据查询条件搜索索引库
        QueryResponse response = solrServer.query(solrQuery);
        //取商品列表
        SolrDocumentList documentList = response.getResults();
        //商品列表
        List<Item> itemList = new ArrayList<>();
        for (SolrDocument solrDocument : documentList) {
            Item item = new Item();
            item.setId((Long) solrDocument.get("id"));
            //取高亮显示
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (null != list && !list.isEmpty()) {
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            item.setPrice((Long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));

            itemList.add(item);
        }
        SearchResult result = new SearchResult();
        //商品列表
        result.setItemList(itemList);
        //总记录数据
        result.setRecordCount(documentList.getNumFound());

        return result;
    }

}
