package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checkout")
public class CheckOutController {
    @RequestMapping("query")
    //这个标标签表示只要有一个权限就能访问
    public String view(){
        return "checkout/checkout";
    }
}
