package com.szm.search.service.impl;

import com.szm.pojo.RuigouResult;
import com.szm.search.mapper.ItemMapper;
import com.szm.search.pojo.Item;
import com.szm.search.service.IItemService;
import com.szm.util.ExceptionUtil;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer server;
    /**
     * 导入所有商品和商品的描述信息
     * @return
     */
    @Override
    public RuigouResult importAllItems() {
        try {
            List<Item> list=itemMapper.getItemList();
            for (Item item:list){
                SolrInputDocument document=new SolrInputDocument();
                document.setField("id",item.getId());
                document.setField("item_title",item.getTitle());
                document.setField("item_sell_point",item.getSell_point());
                document.setField("item_price",item.getPrice());
                document.setField("item_image",item.getImage());
                document.setField("item_category_name",item.getCategory_name());
                document.setField("item_desc",item.getItem_desc());
                server.add(document);
                server.commit();
            }
        } catch (Exception e) {
            return RuigouResult.build(500, ExceptionUtil.getStackTrave(e));

        }
        return RuigouResult.ok();
    }
}
