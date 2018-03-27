package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("dictionary")
public class DictionaryController {


    @Autowired
    private IDictionaryService dictionaryService;

    @RequiresPermissions(value={"dictionary:view","数据字典页面"},logical= Logical.OR)
    @RequestMapping("view")
    public String view(){

        return "dictionary";
    }




/*返回查询的数据,显示在部门的页面*/
    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {

        return dictionaryService.query(qo);
    }

    /*提供其他的页面查询数据*/
    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {

        return dictionaryService.selectAll();
    }



    /*添加部门*/
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Dictionary dictionary) {
        JsonResult jsonUtil = new JsonResult();
        try {

            dictionaryService.saveOrUpdate(dictionary);
        }catch (Exception e){
            e.printStackTrace();
            jsonUtil.mark("操作失败");
        }
        return jsonUtil;
    }
    /*添加部门*/
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JsonResult jsonUtil = new JsonResult();
        try {

            dictionaryService.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonUtil.mark("操作失败");
        }
        return jsonUtil;
    }


    @RequestMapping("changeState")
    @ResponseBody
    public Object changeState(Long id){
        JsonResult result = new JsonResult();
        try {
            dictionaryService.changeState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }
}
