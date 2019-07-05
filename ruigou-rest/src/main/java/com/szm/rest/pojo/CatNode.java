package com.szm.rest.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 商品分类节点,
 */

public class CatNode {

    //名称是接口中定义的名称
    //类目名称
    @JsonProperty("n")
    private String name;
    //类目ur
    @JsonProperty("u")
    private String url;
    //子类目
    @JsonProperty("i")
    private List<?> item;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<?> getItem() {
        return item;
    }

    public void setItem(List<?> item) {
        this.item = item;
    }
}
