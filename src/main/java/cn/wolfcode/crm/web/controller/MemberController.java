package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.query.MemberQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IBonusPointRecordService;
import cn.wolfcode.crm.service.IMemberService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private IMemberService memberService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return memberService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value = {"member:view", "会员列表"}, logical = Logical.OR)
    public String view( Model model) {
        Map<String, Object> map = memberService.selectMemberMsg();
        model.addAttribute("result",map );
        return "member";
    }

    //会员充值的页面
    @RequestMapping("topUpView")
    @RequiresPermissions(value={"member:memberTopUp","会员充值列表"},logical = Logical.OR)
    public String topUpView(){
        return "memberTopUp";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(MemberQueryObject qo){

        return memberService.query(qo);
    }

    @RequestMapping("listByKeyword")
    @ResponseBody
    public List<Member> listByKeyword(MemberBonusPointQueryObject qo) {

        return memberService.queryByKeyword(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id) {
        JsonResult result = new JsonResult();
        try {
            memberService.deleteByPrimaryKey(id);
        } catch (Exception e) {
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

    @RequestMapping("clearPoints")
    @ResponseBody
    public Object clearPoints(Long id) {
        JsonResult result = new JsonResult();
        try {
            memberService.clearPoints(id);

        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }
    @RequestMapping("checkPass")
    @ResponseBody
    public Object checkPass(String password,Long id) {
        JsonResult result = new JsonResult();
        try {
            memberService.checkPass(password,id);

        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }
    @RequestMapping("checkPoints")
    @ResponseBody
    public Object checkPoints(Integer points,Long id) {
        JsonResult result = new JsonResult();
        try {
            memberService.checkPoints(points,id);

        } catch (Exception e) {
            result.mark(e.getMessage());
        }
        return result;
    }
    /*修改密码*/
    @RequestMapping("updatePasswordById")
    @ResponseBody
    public Object updatePasswordById(Member meber){
        JsonResult result = new JsonResult();
        try {
            memberService.updatePasswordById(meber);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }

}
