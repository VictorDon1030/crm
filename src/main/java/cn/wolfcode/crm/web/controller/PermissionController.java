package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    IPermissionService permissionService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll() {
        return permissionService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"permission:view","权限列表"},logical = Logical.OR)
    public String view() {
        return "permission";
    }

    @RequestMapping("load")
    @ResponseBody
    public Object load() {
        JsonResult result = new JsonResult();
        try {
            permissionService.reload();
        } catch (Exception e) {
            result.mark("加载失败");
        }
        return result;
    }

}
