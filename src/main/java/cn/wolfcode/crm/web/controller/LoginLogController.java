package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.LoginLogQuery;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ILoginLogService;
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
@RequestMapping("loginLog")
public class LoginLogController {

    @Autowired
    ILoginLogService loginLogService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return loginLogService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"loginLog:view","部门列表"},logical = Logical.OR)
    public String view(){
        return "loginLog";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(LoginLogQuery qo){
        return loginLogService.query(qo);
    }
}
