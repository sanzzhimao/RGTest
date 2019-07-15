package com.szm.portal.service.impl;



import com.szm.pojo.RuigouResult;
import com.szm.pojo.TbContent;
import com.szm.portal.service.IContentService;
import com.szm.util.HttpClientUtil;
import com.szm.util.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ContentServiceImpl implements IContentService {


    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;


    /**
     * 调用rest服务，获得内容列表
     * @return
     */
    @Override
    public String getContentList() {
        //使用工具类调用服务
        String result= HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
        try {
            RuigouResult result1=RuigouResult.formatToList(result, TbContent.class);
            List<TbContent> list=(List<TbContent>)result1.getData();
            List<Map> resultList=new ArrayList<>();
            for (TbContent content:list){
                Map map=new HashMap<>();
                //图片信息
                map.put("src", content.getPic());
                map.put("height",240);
                map.put("width", 670);
                map.put("srcB", content.getPic2());
                map.put("heightB",240);
                map.put("widthB",550 );
                map.put("href",content.getUrl());
                map.put("alt", content.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
