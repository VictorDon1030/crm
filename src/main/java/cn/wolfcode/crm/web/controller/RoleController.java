package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    IRoleService roleService;

    @RequestMapping("view")
    @RequiresPermissions(value={"role:view","角色列表"},logical = Logical.OR)
    public String view(){
        return "role";
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return roleService.selectAll();
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return roleService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long roleId){
        JsonResult result = new JsonResult();
        try {
            roleService.deleteByPrimaryKey(roleId);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }

    @RequestMapping("selectPermission")
    @ResponseBody
    public List<Permission> selectPermission(Long id){
        List<Permission> permissions = new ArrayList<>();
        if (id != null){
            permissions = roleService.selectByPrimaryKey(id).getPermissions();
        }
        return permissions;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Role entity,Long[] ids){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() != null){
                roleService.updateByPrimaryKey(entity,ids);
            } else {
                roleService.insert(entity,ids);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }

        return result;
    }
}
