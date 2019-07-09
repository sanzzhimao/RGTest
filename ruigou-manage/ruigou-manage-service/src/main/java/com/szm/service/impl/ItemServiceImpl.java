package com.szm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.szm.search.mapper.TbItemMapper;
import com.szm.pojo.EasyUIDateGrid;
import com.szm.pojo.TbItem;
import com.szm.pojo.TbItemExample;
import com.szm.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private TbItemMapper itemMapper;

    /**
     * 根据id查询商品
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(Long itemId) {
        //添加查询条件
        TbItemExample example=new TbItemExample();
        TbItemExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(itemId);
        //查询
        List<TbItem> list=itemMapper.selectByExample(example);
        if(list!=null && list.size()>0){
            TbItem item=list.get(0);
            return item;
        }
        return null;
    }

    /**
     * 查询所有商品
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EasyUIDateGrid getItemList(int page, int rows) {
        //执行查询，并实现分页
        TbItemExample example=new TbItemExample();
        //设置分页信息
        PageHelper.startPage(page,rows);
        List<TbItem> itemslist=itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo=new PageInfo<>(itemslist);
        long total=pageInfo.getTotal();
        EasyUIDateGrid result=new EasyUIDateGrid();
        result.setTotal(total);
        result.setRows(itemslist);
        return result;
    }
}
