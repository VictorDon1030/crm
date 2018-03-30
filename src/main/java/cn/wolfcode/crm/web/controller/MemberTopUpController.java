package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberTopUpService;
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
@RequestMapping("memberTopUp")
public class MemberTopUpController {

    @Autowired
    IMemberTopUpService memberTopUpService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return memberTopUpService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"memberTopUp:view","部门列表"},logical = Logical.OR)
    public String view(){
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
        return memberTopUpService.selecToptItemByMemberId(id);
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
