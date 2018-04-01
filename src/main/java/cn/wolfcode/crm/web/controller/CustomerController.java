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
public class CustomerController {

    @RequestMapping("zzh")
    public String zzh(){
        return "zzh";
    }

    @RequestMapping("gzl")
    public String gzl(){
        return "gzl";
    }
}
