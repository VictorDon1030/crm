package cn.wolfcode.crm.web.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接收页面参数,并实现对会员积分管理页面的跳转控制
 * @author 应举
 *
 */
@Controller
@RequestMapping("bonusPoint")
public class BonusPointController {
    @RequiresPermissions(value={"Gift:view","积分管理"},logical = Logical.OR)
    @RequestMapping("view")
    public String bonusPointManage() throws Exception {
        return "bonusPoint";
    }
}
