package com.szm.rest.service.impl;

import com.szm.mapper.TbItemCatMapper;
import com.szm.pojo.*;
import com.szm.rest.dao.IJedisClient;
import com.szm.rest.pojo.CatNode;
import com.szm.rest.pojo.CatResult;
import com.szm.rest.service.ItemCatService;
import com.szm.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper mapper;

    @Autowired
    private IJedisClient jedisClient;

    @Value("${ITEM_CAT_KEY}")
    private String ITEM_CAT_KEY;
    /**
     * 循环查找，先查找以及节点，取出后得到一个列表，遍历每个以及节点，用这个节点的id作为别人的parentid条件再查二级节点，叶子节点同理
     * 递归可解决多层问题，不确定层数问题，最后一层不是节点而是一个字符串
     * @return
     */
    @Override
    public CatResult getItemCat() {

        CatResult result=new CatResult();
        //从第一层节点开始查找
        result.setData(getDataRedis(0));
        return result;
    }

    private List<?> getDataRedis(long parentId){
        try {
            String result=jedisClient.hget(ITEM_CAT_KEY,parentId+"");
            if (!StringUtils.isBlank(result)){
                List<?> list= JsonUtils.jsonToList(result,CatNode.class);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //緩存中不存在相關數據
        List list=getData(parentId);
        try {
            jedisClient.hset(ITEM_CAT_KEY,parentId+"",JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 查找所有节点方法
     */
    private List<?> getData(long parentId){
        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list=mapper.selectByExample(example);
        List resultList=new ArrayList<>();
        int count=0;
        for (TbItemCat t:list){
            CatNode node=new CatNode();
            if (t.getIsParent()){
                //含有子节点
                if (parentId==0){
                    //一级节点，名称带有a标签
                    node.setName("<a href='/product/"+t.getId()+".html'>"+t.getName()+"</a>");

                    //计数
                    count++;
                    if (parentId==0 && count>=14) break;
                }else {
                    //二級节点，名称是类目的名字
                    node.setName(t.getName());
                }
                //设置节点url
                node.setUrl("/product/"+t.getId()+".html");
                //设置节点子节点，回调函数，再次查询
                node.setItem(getData(t.getId()));
                resultList.add(node);
            }else {
                //叶子节点，只用显示一个字母串
                resultList.add("/product/"+t.getId()+".html|"+t.getName());
            }
        }
        return resultList;
    }
}
