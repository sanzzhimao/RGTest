package com.szm.controller;


import com.szm.pojo.EasyUIDateGrid;
import com.szm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password, ModelMap modelMap){
        if(username!= "root"){
            modelMap.addAttribute("msg","用户不存在!");
        }else {
            if(password == "123456"){
                modelMap.addAttribute("msg","登陆成功!");
            }else {
                modelMap.addAttribute("msg","密码错误");
            }
        }
        return "index";
    }


    @RequestMapping("/user/user")

    @ResponseBody
    public EasyUIDateGrid getUserList(int page, int rows){
        System.out.println(page);
       EasyUIDateGrid result = userService.getUserList(page,rows);
        return result;
    }

    @RequestMapping("/user/mohu")
    @ResponseBody
    public EasyUIDateGrid getUserNameList(int page, int rows,String name){
        System.out.println(page);
        EasyUIDateGrid result = userService.getUserNameList(page,rows,name);
        return result;
    }
}
