package com.szm.portal.service.impl;

import com.szm.pojo.RuigouResult;
import com.szm.portal.pojo.CartItem;
import com.szm.portal.service.ICartService;
import com.szm.util.CookieUtils;
import com.szm.util.HttpClientUtil;
import com.szm.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    //服务url
    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    //商品服务url
    @Value("${ITEMS_ITEM_URL}")
    private  String ITEMS_ITEM_URL;
    //cookie购物车商品中的key
    @Value("${CART_ITEMS_LIST_KEY}")
    private String CART_ITEMS_LIST_KEY;
    //购物车cookie生存期
    @Value("${CART_ITEMS_EXPIRE_TIME}")
    private Integer CART_ITEMS_EXPIRE_TIME;


    /**
     *添加购物车商品
      * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public RuigouResult addItem(Long itemId, HttpServletRequest request, HttpServletResponse response) {
        //根据商品id查询商品信息
       CartItem cartItem= getCartItemById(itemId);
        //取出cookie中的购物车商品列表
        List<CartItem>  cartItems=getItemListFromeCookies(request);
        //判断cookie中是否存在相同的商品id
        Boolean isExists = false;
        for(CartItem c:cartItems){
            if (c.getItemid()==itemId){
                //购物车有该商品,增加该商品数量
                c.setNum(c.getNum()+1);
                isExists=true;
                break;
            }
        }
        //如果购物车当中不存在该商品id,就向购物车列表添加一个商品
        if (isExists==false){
            //设置商品数量num=1
            cartItem.setNum(1);
            //把购物车商品信息添加到购物车集合中
            cartItems.add(cartItem);
        }
        //把购物车商品信息写入cookie中
        CookieUtils.setCookie(request,response,CART_ITEMS_LIST_KEY,JsonUtils.objectToJson(cartItems),
                CART_ITEMS_EXPIRE_TIME,true);
        return RuigouResult.ok(cartItems);
    }

    /**
     * 查询购物车
     * @param request
     * @return
     */
    @Override
    public List<CartItem> getCartList(HttpServletRequest request) {
        //从cookie取出商品列表
        List<CartItem> cartItems=getItemListFromeCookies(request);
        return cartItems;
    }

    /**
     * 修改商品数量
     *
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    @Override
    public RuigouResult changeItemNums(Long itemId, int num, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取出商品列表
        List<CartItem> cartItems=getItemListFromeCookies(request);
        //从商品列表取出对应itemId的商品信息
        for (CartItem i:cartItems){
            if(i.getItemid()==itemId){
                i.setNum(num);
                break;
            }
        }
        //把商品信息写入cookie
        CookieUtils.setCookie(request,response,CART_ITEMS_LIST_KEY,JsonUtils.objectToJson(cartItems),
                CART_ITEMS_EXPIRE_TIME,true);
        return RuigouResult.ok();
    }

    /**
     * 删除购物车的商品信息
     *
     * @param itemId
     * @param response
     * @param request
     * @return
     */
    @Override
    public List<CartItem> deleteCartItems(Long itemId, HttpServletResponse response, HttpServletRequest request) {
       //从购物车取出商品列表
        List<CartItem> list=getItemListFromeCookies(request);
       //取出购物车的商品信息
       for (CartItem i:list){
           if(i.getItemid()==itemId){
               list.remove(i);
               break;
           }
       }
       //刷新购物车中的商品信息
        CookieUtils.setCookie(request,response,CART_ITEMS_LIST_KEY,JsonUtils.objectToJson(list),
                CART_ITEMS_EXPIRE_TIME,true);
        return list;
    }


    /*根据id查询商品信息*/
    private  CartItem getCartItemById(Long itemId){
        String resultStr = HttpClientUtil.doGet(REST_BASE_URL+ITEMS_ITEM_URL+itemId);
        //转换成RuiGouResult
        RuigouResult result= RuigouResult.formatToPojo(resultStr,CartItem.class);
        //取商品信息
        CartItem cartItem=null;
        if(result.getStatus()==200){
            cartItem = (CartItem) result.getData();
        }
        return cartItem;
    }

    /*从cookkie获取购物车商品信息*/
    private  List<CartItem> getItemListFromeCookies(HttpServletRequest request) {
        String cartItemsStr = CookieUtils.getCookieValue(request, CART_ITEMS_LIST_KEY, true);
        //如果不为空转成java对象
        List<CartItem> cartItems = null;
        if (!StringUtils.isBlank(cartItemsStr)) {
            cartItems = JsonUtils.jsonToList(cartItemsStr, CartItem.class);
        } else {
            cartItems = new ArrayList<>();
        }
        return cartItems;
    }

}
