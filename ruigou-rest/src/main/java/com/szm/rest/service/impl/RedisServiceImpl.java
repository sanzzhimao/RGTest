package com.szm.rest.service.impl;

import com.szm.pojo.RuigouResult;
import com.szm.rest.dao.IJedisClient;
import com.szm.rest.service.IRedisService;
import com.szm.util.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class RedisServiceImpl implements IRedisService {

    @Autowired
    private IJedisClient jedisClient;

    @Value("${INDEX_CONTENT_BigAder_KEY}")
    private String INDEX_CONTENT_BigAder_KEY;

    @Override
    public RuigouResult syncContent(long contentCid) {
        try{
            jedisClient.hdel(INDEX_CONTENT_BigAder_KEY,contentCid+"");
        }catch (Exception e){
            return RuigouResult.build(500, ExceptionUtil.getStackTrave(e));
        }
        return RuigouResult.ok();
    }
}
