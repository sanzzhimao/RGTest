package com.szm.portal.controller;

import com.szm.pojo.RuigouResult;
import com.szm.portal.pojo.CartItem;
import com.szm.portal.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;



@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    /**
     * 添加商品信息
     * @param itemId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/add/{itemId}")
    public String addCartItem(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response, Model model){
        //添加商品信息
        RuigouResult result=cartService.addItem(itemId,request,response);
        //错误信息
        if(result.getStatus()!=200){
            model.addAttribute("message",result.getMsg());
            return "error/exception" ;
        }

        model.addAttribute("cartList",result.getData());
        return "cart" ;
    }

    /**
     * 查询购物车
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/cart")
    public String getCarList(HttpServletRequest request,Model model){
        List<CartItem> cartItems=cartService.getCartList(request);
        model.addAttribute(cartItems);
        return "cart";
    }

    /**
     * 修改购物车商品数量
     * @param itemId
     * @param num
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public String upDateNumById(@PathVariable Long itemId,@PathVariable int num,HttpServletRequest request,HttpServletResponse response,Model model){
        RuigouResult result=cartService.changeItemNums(itemId,num,request,response);
        model.addAttribute(request);
        return "cart";
    }

    @RequestMapping("/delete/{itemId}")
    public String deleteCartItemById(@PathVariable Long itemId, HttpServletRequest request, HttpServletResponse response, Model model){
      List<CartItem> cartItems=cartService.deleteCartItems(itemId,response,request);
      model.addAttribute(cartItems);
      return "cart";
    }
}
