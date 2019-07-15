package com.szm.rest.service.impl;

import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContentExample;
import com.szm.rest.dao.IJedisClient;
import com.szm.mapper.TbContentMapper;
import com.szm.pojo.TbContent;
import com.szm.rest.service.IContentService;
import com.szm.util.ExceptionUtil;
import com.szm.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    private TbContentMapper mapper;
    @Autowired
    private IJedisClient jedisClient;
    @Value("${REDIS_CONTENT_KEY}")
    private String REDIS_CONTENT_KEY;
    /**
     * 获取(对应位置-类目id下所有内容)内容列表
     * @param contentCid
     * @return
     */
    @Override
    public RuigouResult getContentList(long contentCid) {
        //未使用redis
//        TbContentExample example=new TbContentExample();
//        TbContentExample.Criteria criteria=example.createCriteria();
//        criteria.andCategoryIdEqualTo(contentCid);
//        return mapper.selectByExample(example);
        //redis
        //先读取缓存的逻辑，不影响正常逻辑
        try {
            String result=jedisClient.hget(REDIS_CONTENT_KEY,contentCid+"");
            if (!StringUtils.isBlank(result)){
                List<TbContent> list= JsonUtils.jsonToList(result,TbContent.class);
                return RuigouResult.ok(list);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //缓存中不存在则继续往下执行，在数据库中查找
        List<TbContent> list=null;
        try {
            TbContentExample example=new TbContentExample();
            TbContentExample.Criteria criteria=example.createCriteria();
            list=mapper.selectByExample(example);

        }catch (Exception e){
            e.printStackTrace();
            return RuigouResult.build(500, ExceptionUtil.getStackTrave(e));
        }
        //把内容添加到缓存中
        try {
            jedisClient.hset(REDIS_CONTENT_KEY,contentCid+"",JsonUtils.objectToJson(list));
        }catch (Exception e){
            e.printStackTrace();
        }
        return RuigouResult.ok();

    }
}
