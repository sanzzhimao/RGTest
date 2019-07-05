package com.szm.rest.pojo;

import java.util.List;

/**
 * 分类结果json串
 */
public class CatResult {

    //类目,所有的分类
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
