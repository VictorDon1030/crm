package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IMaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("maxType")
public class MaxTypeController {
    @Autowired
    private IMaxTypeService maxTypeService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return maxTypeService.selectAll();
    }
}
