package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.SystemLogQuery;
import cn.wolfcode.crm.service.ISystemLogService;
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
@RequestMapping("systemLog")
public class SystemLogController {

    @Autowired
    ISystemLogService systemLogService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return systemLogService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"systemLog:view","部门列表"},logical = Logical.OR)
    public String view(){
        return "systemLog";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SystemLogQuery qo){
        return systemLogService.query(qo);
    }
}
