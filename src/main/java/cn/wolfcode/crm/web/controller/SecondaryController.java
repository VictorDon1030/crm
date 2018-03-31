package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Secondary;
import cn.wolfcode.crm.mapper.SecondaryMapper;
import cn.wolfcode.crm.service.ISecondaryService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("secondary")
public class SecondaryController {

    @Autowired
    private ISecondaryService secondaryService;
    @Autowired
    private SecondaryMapper secondaryMapper;

    @RequestMapping("view")
    public String view(){
        return "secondary";
    }

    //分类商品列表
    @RequestMapping("list")
    @ResponseBody
    public Object list(){

        return secondaryService.listAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Secondary entity){
        JsonResult jsonResult = new JsonResult();
        try {
            secondaryService.saveOrUpdate(entity);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult jsonResult = new JsonResult();
        try {
            secondaryService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
}
























