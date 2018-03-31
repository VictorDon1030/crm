package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.service.IMemberTopUpService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("memberTopUp")
public class MemberTopUpController {

    @Autowired
    IMemberTopUpService memberTopUpService;
    @Autowired
    IDictionaryItemService dictionaryItemService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return memberTopUpService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"memberTopUp:view","会员充值列表"},logical = Logical.OR)
    public String view(Model model){
        List<DictionaryItem> payment = dictionaryItemService.selectItemByDictionarySn("payment");
        model.addAttribute("payment",payment);
        return "memberTopUp";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return memberTopUpService.query(qo);
    }

    //根据会员的id传充值的明细
    @RequestMapping("selecToptItemByMemberId")
    @ResponseBody
    public Object selecToptItemByMemberId(Long id){
        List<MemberTopUp> topUps = memberTopUpService.selecToptItemByMemberId(id);
        return topUps;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            memberTopUpService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(MemberTopUp entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                memberTopUpService.insert(entity);
            } else {
                memberTopUpService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
            e.printStackTrace();
        }
        return result;
    }
}
