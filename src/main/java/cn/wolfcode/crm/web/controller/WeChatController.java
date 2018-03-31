package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.WeChat;
import cn.wolfcode.crm.service.IWeChatService;
import cn.wolfcode.crm.util.JsonResult;
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
@RequestMapping("weiChat")
public class WeChatController {

    @Autowired
    IWeChatService weChatService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return weChatService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"weChat:view","支付绑定列表"},logical = Logical.OR)
    public String view(){
        return "weChat";
    }
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long chatId){
        JsonResult result = new JsonResult();
        try {
            weChatService.deleteByPrimaryKey(chatId);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(WeChat entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() != null){
                if (entity.getApplyer().getId() == null) {
                  entity.getApplyer().setId(-1L);
                }
                weChatService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }
        return result;
    }
}
