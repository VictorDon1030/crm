package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.MaxType;
import cn.wolfcode.crm.domain.MinType;
import cn.wolfcode.crm.service.IMaxTypeService;
import cn.wolfcode.crm.util.JsonResult;
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

    /**
     * 添加大分类
     */
    @RequestMapping("insertMaxType")
    @ResponseBody
    public Object insertMinType(MaxType maxType){
        JsonResult result=new JsonResult();
        try{

            maxTypeService.insert(maxType);
        }catch (Exception e){
            result.mark(e.getMessage());
        }
        return  result;
    }
}
