package com.szm.rest.controller;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class SolrJTest {

    @Test
    public void updateDocumentTest() throws IOException, SolrServerException {
        SolrServer solrServer=new HttpSolrServer("http://192.168.15.128:9000/solr");
        SolrInputDocument document=new SolrInputDocument();
        document.addField("id","test001");
        document.addField("item_title","test001");
        document.addField("item_price",123);
        solrServer.add(document);
        solrServer.commit();
    }
    @Test
    public void del() throws IOException, SolrServerException {
        SolrServer solrServer=new HttpSolrServer("http://192.168.15.128:9000/solr");
        solrServer.deleteByQuery("*:*");
        solrServer.commit();
    }

}
