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
public class AllChartsController {

    @RequestMapping("allchart")
    public String allCharts(){
        return "allchart";
    }
}
