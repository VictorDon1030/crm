package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.query.GiftQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IGiftService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("gift")
public class GiftController {

    @Autowired
    IGiftService giftService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return giftService.selectAll();
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(GiftQueryObject qo){
        return giftService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            giftService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Gift entity){
        JsonResult result = new JsonResult();
        try {
                giftService.saveOrUpdate(entity);
        } catch (Exception e){
            result.mark("操作失败");
        }
        return result;
    }

}
