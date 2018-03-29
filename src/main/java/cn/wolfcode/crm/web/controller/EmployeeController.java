package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.EmployeeQuery;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IRoleService;
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
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    IEmployeeService employeeService;
    @Autowired
    IRoleService roleService;

    @RequestMapping("view")
    @RequiresPermissions(value={"employee:view","员工列表"},logical = Logical.OR)
    public String view(){
        return "employee";
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return employeeService.selectAll();
    }


    @RequestMapping("list")
    @ResponseBody
    public PageResult list(EmployeeQuery qo){
        return employeeService.query(qo);
    }

    @RequestMapping("selectRoleByEmpId")
    @ResponseBody
    public List<Long> selectRoleByEmpId(Long id){
        return roleService.selectRoleIdByEmpId(id);
    }

    @RequestMapping("changeState")
    @ResponseBody
    public Object changeState(Long id){
        JsonResult result = new JsonResult();
        try {
            employeeService.changeState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Employee entity,Long[] ids){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() != null){
                employeeService.updateByPrimaryKey(entity,ids);
            } else {
                entity.setState(true);
                employeeService.insert(entity,ids);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }

        return result;
    }
}
