package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
@RequestMapping("member")
public class MmberController {

    @Autowired
    IMemberService memberService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return memberService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"member:view","会员列表"},logical = Logical.OR)
    public String view(){
        return "member";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return memberService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            memberService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Member entity){
        JsonResult result = new JsonResult();


        try {
            if (entity.getId() == null){
                entity.setState(true);
                memberService.insert(entity);
            } else {
                memberService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }
        return result;
    }
    @RequestMapping("changeState")
    @ResponseBody
    public Object changeState(Long id){
        JsonResult result = new JsonResult();
        try {
            memberService.changeState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }
}
