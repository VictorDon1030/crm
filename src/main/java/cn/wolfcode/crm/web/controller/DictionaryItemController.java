package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.DictionaryItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("dictionaryItem")
public class DictionaryItemController {


    @Autowired
    private IDictionaryItemService dictionaryItemService;

    @RequiresPermissions(value={"dictionaryItem:view","数据字典明细页面"},logical= Logical.OR)
    @RequestMapping("view")
    public String view(){

        return "dictionaryItem";
    }




/*返回查询的数据,显示在部门的页面*/
    @RequestMapping("list")
    @ResponseBody
    public Object list(QueryObject qo) {

        return dictionaryItemService.query(qo);
    }

    /*提供其他的页面查询数据*/
    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {

        return dictionaryItemService.selectAll();
    }
    /*添加部门*/
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(DictionaryItem dictionaryItem) {
        JsonResult jsonUtil = new JsonResult();
        try {

            dictionaryItemService.saveOrUpdate(dictionaryItem);
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

            dictionaryItemService.deleteByPrimaryKey(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonUtil.mark("操作失败");
        }
        return jsonUtil;
    }
    /*添加明细*/
    @RequestMapping("selectItemByDictionaryId")
    @ResponseBody
    public Object selectItemByDictionaryId(DictionaryItemQueryObject qo) {

            PageResult result = dictionaryItemService.query(qo);
        return result;
    }





}
