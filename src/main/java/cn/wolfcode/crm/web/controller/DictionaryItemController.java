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

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("dictionaryItem")
public class DictionaryItemController {


    @Autowired
    private IDictionaryItemService dictionaryItemService;

    @RequiresPermissions(value = {"dictionaryItem:view", "数据字典明细页面"}, logical = Logical.OR)
    @RequestMapping("view")
    public String view() {

        return "dictionaryItem";
    }


    /*根据sn查询对应的数据字典*/
    @RequestMapping("selectItemByDictionarySn")
    @ResponseBody
    public Object selectItemByDictionarySn(String dictionarySn) {

        return dictionaryItemService.selectItemByDictionarySn(dictionarySn);
    }


/*返回查询的数据,显示在明细的页面*/
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
        } catch (Exception e) {
            e.printStackTrace();
            jsonUtil.mark("操作失败");
        }
        return jsonUtil;
    }

    /*删除*/
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JsonResult jsonUtil = new JsonResult();
        try {

            dictionaryItemService.deleteByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            jsonUtil.mark("操作失败");
        }
        return jsonUtil;
    }

    /*根据字典id查询明细*/
    @RequestMapping("selectItemByDictionaryId")
    @ResponseBody
    public Object selectItemByDictionaryId(DictionaryItemQueryObject qo) {

        PageResult result = dictionaryItemService.query(qo);
        return result;
    }


    @RequestMapping("changeState")
    @ResponseBody
    public Object changeState(Long id) {
        JsonResult result = new JsonResult();
        try {
            dictionaryItemService.changeState(id);
        } catch (Exception e) {
            result.mark("设置失败");
        }
        return result;
    }

    /**
     * 根据数据字典明细id查询数据字典内容
     * @param id
     * @return
     * @throws Exception
     */
    /*
    @RequestMapping("select")
    @ResponseBody
    public Object select(Long id) throws Exception {
        return dictionaryItemService.selectByPrimaryKey(id);
    }*/


}
