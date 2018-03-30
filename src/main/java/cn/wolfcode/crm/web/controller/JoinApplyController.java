package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.JoinApply;
import cn.wolfcode.crm.domain.WeChat;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IJoinApplyService;
import cn.wolfcode.crm.service.IWeChatService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
@RequestMapping("joinApply")
public class JoinApplyController {

    @Autowired
    IJoinApplyService joinApplyService;
    @Autowired
    IWeChatService weChatService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return joinApplyService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"joinApply:view","支付申请列表"},logical = Logical.OR)
    public String view(){
        return "joinApply";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return joinApplyService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long appId){
        JsonResult result = new JsonResult();
        try {
            joinApplyService.deleteByPrimaryKey(appId);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(JoinApply entity, WeChat chat){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                joinApplyService.insert(entity);

                if (chat != null){
                    chat.setApplyer(entity);
                    weChatService.insert(chat);
                }
            } else {
                joinApplyService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }
        return result;
    }
}
