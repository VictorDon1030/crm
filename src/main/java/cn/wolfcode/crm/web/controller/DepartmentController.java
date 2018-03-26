package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
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
@RequestMapping("department")
public class DepartmentController {

    @Autowired
    IDepartmentService departmentService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return departmentService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"department:view","部门列表"},logical = Logical.OR)
    public String view(){
        return "department";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return departmentService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            departmentService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Department entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                entity.setState(true);
                departmentService.insert(entity);
            } else {
                departmentService.updateByPrimaryKey(entity);
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
            departmentService.changeState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }
}
