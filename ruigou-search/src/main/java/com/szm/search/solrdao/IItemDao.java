package com.szm.search.solrdao;

import com.szm.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

public interface IItemDao {

    SearchResult searchItem(SolrQuery solrQuery) throws Exception ;
}
