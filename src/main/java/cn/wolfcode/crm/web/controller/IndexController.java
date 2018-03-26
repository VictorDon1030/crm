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
}
