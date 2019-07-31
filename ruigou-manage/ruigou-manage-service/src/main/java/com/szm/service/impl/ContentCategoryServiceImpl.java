package com.szm.service.impl;

import com.szm.mapper.TbContentCategoryMapper;
import com.szm.pojo.EUTreeNode;
import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContentCategory;
import com.szm.pojo.TbContentCategoryExample;
import com.szm.service.IContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements IContentCategoryService {

    @Autowired
    private TbContentCategoryMapper mapper;
    /**
     * 获取内容分类子节点
     * @param parentId
     * @return
     */
    @Override
    public List<EUTreeNode> getContentCat(long parentId) {
        TbContentCategoryExample example=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list=mapper.selectByExample(example);
        List<EUTreeNode> resultList=new ArrayList<>();
        for (TbContentCategory t:list){
            EUTreeNode node=new EUTreeNode();
            node.setId(t.getId());
            node.setText(t.getName());
            node.setState(t.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    /**
     * 增加分类
     * @param parentId
     * @param name
     * @return
     */
    @Override
    public RuigouResult insertContentCat(long parentId, String name) {
        //TbContentCategory信息补全
        TbContentCategory contentCategory=new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        contentCategory.setParentId(parentId);
        contentCategory.setStatus(1);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        mapper.insert(contentCategory);
        TbContentCategory parentContentCategory=mapper.selectByPrimaryKey(parentId);
        //判断父节点的isParent是否为true
        if(!parentContentCategory.getIsParent()){
            parentContentCategory.setIsParent(true);
            //设置完true之后，更新父节点,更新父节点使用mapper的updatebyPrimaryKey
            //参数需要传一个修改后的父节点对象
            mapper.updateByPrimaryKey(parentContentCategory);
        }
        //返回结果就返回一个RuigouResult,并且要把创建的新节点pojo对象返回
        return RuigouResult.ok(contentCategory);
    }

    /**
     * 修改分类
     * @param id
     * @param name
     * @return RuigouResult
     */
    @Override
    public RuigouResult updateContentCat(long id, String name) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        tbContentCategory.setUpdated(new Date());
//        TbContentCategoryExample example=new TbContentCategoryExample();
//        TbContentCategoryExample.Criteria criteria=example.createCriteria();
//        criteria.andIdEqualTo(id);
        mapper.updateByPrimaryKeySelective(tbContentCategory);
        return RuigouResult.ok();
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @Override
    public RuigouResult delectContentCat(long id) {
        TbContentCategory tbContentCategory=mapper.selectByPrimaryKey(id);
        List<TbContentCategory> list=new ArrayList<>();
        TbContentCategoryExample example=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria=example.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        list=mapper.selectByExample(example);
        if(list.size()==1){
            TbContentCategory tbContentCategory1=new TbContentCategory();
            tbContentCategory1.setId(tbContentCategory.getParentId());
            tbContentCategory1.setIsParent(false);
            tbContentCategory1.setUpdated(new Date());
            mapper.updateByPrimaryKeySelective(tbContentCategory1);
        }
        mapper.deleteByPrimaryKey(id);
        TbContentCategoryExample example1=new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria1=example1.createCriteria();
        criteria1.andParentIdEqualTo(id);
        mapper.deleteByExample(example1);

        return RuigouResult.ok();
    }
}

