package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(){

        return "redirect:/login.jsp";
    }

    //跳转主页面
    @RequestMapping("main")
    public String main(){

        return "main";
    }

    //跳转系统设置页面
    @RequestMapping("syshome")
    public String syshome(){

        return "syshome";
    }

    //跳转我的店铺
    @RequestMapping("myshop")
    public String myhsop(){

        return "myshop";
    }
}
