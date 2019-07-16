package com.szm.portal.service;

import com.szm.pojo.RuigouResult;
import com.szm.portal.pojo.CartItem;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface ICartService {

    /**
     * 添加购物车到cookie
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    public RuigouResult addItem(Long itemId, HttpServletRequest request, HttpServletResponse response);

    /**
     * 取出购物车商品列表
     * @param request
     * @return
     */
    public List<CartItem> getCartList(HttpServletRequest request);

    /**
     * 修改商品数量
     * @param itemId
     * @param request
     * @param response
     * @return
     */
    public RuigouResult changeItemNums(Long itemId, int num, HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除购物车的商品信息
     * @param itemId
     * @param response
     * @param request
     * @return
     */
    public List<CartItem> deleteCartItems(Long itemId,HttpServletResponse response,HttpServletRequest request);
}
