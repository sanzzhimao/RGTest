package com.szm.service;

/*
* 查询商品描述
* */


import com.szm.pojo.TbItemDesc;

public interface ItemInquiriesService {
    //通过item_id查询商品描述
    public TbItemDesc getItemDescById(long itemId);
    //通过item_id删除商品描述
    public void removeItemDescById(long itemId);
    //修改商品描述
    public void modifyItemDescByItem(TbItemDesc itemDesc);
}
