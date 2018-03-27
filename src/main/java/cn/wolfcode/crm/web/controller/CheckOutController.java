package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checkout")
public class CheckOutController {
    @RequestMapping("view")
    public String view(){
        return "checkout/checkout";
    }
}
