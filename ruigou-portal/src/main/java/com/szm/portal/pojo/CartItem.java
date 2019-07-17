package com.szm.portal.pojo;


public class CartItem {
    //商品ID
    private int itemid;
    //商品图片
    private String image;
    //商品信息
    private String title;
    //单价
    private Long price;
    //数量
    private int num;

    @Override
    public String toString() {
        return "CartItem{" +
                "itemid=" + itemid +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", num=" + num +
                '}';
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
