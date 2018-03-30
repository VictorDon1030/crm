package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Manager;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IManagerService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
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
@RequestMapping("managerMan")
public class ManagerController {

    @Autowired
    IManagerService managerService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return managerService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"manager:view","管理人员列表"},logical = Logical.OR)
    public String view(){
        return "manager";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return managerService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long mId){
        JsonResult result = new JsonResult();
        try {
            if (mId!=null) {
                managerService.deleteByPrimaryKey(mId);
            }
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Manager entity,String password){
        JsonResult result = new JsonResult();
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        try {
            if (employee  != null && employee.getAdmin() && password.equals(employee.getPassword())) {
                if (entity.getId() == null){
                managerService.insert(entity);
                } else {
                managerService.updateByPrimaryKey(entity);
                }
            } else {
                throw new RuntimeException("权限不足");
            }
        } catch (Exception e){
            result.mark("亲,保存失败"+" , "+e.getMessage());
        }
        return result;
    }
}
