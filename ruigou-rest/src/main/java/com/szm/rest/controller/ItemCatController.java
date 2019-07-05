package com.szm.rest.controller;

import com.szm.rest.pojo.CatResult;
import com.szm.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService serivice;

    /**
     *
     * @param callBack 回调函数参数名
     * @return Object-4.0 返回带有回调函数名称的object
     */
    @RequestMapping(value = "/rest/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset:utf-8")
    @ResponseBody
    public Object getItemCat(String callBack){
        //3.0
        /*CatResult result=serivice.getItemCat();
        //转成json串
        //连接callBack+resultjson串
        //返回js串
        return null;*/


        //4.0
        CatResult result=serivice.getItemCat();
        MappingJacksonValue mappingJacksonValue=new MappingJacksonValue(result);
        mappingJacksonValue.setJsonpFunction(callBack);
        return mappingJacksonValue;
    }


}
