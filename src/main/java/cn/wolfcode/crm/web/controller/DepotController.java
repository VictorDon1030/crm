package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepotService;
import cn.wolfcode.crm.service.IEmployeeService;
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
 * @author Administrator
 * @date 2018/3/27 23:29
 */
@Controller
@RequestMapping({"depot"})
public class DepotController {
    @Autowired
    private IDepotService depotService;
    @Autowired
    private IEmployeeService employeeService;

    public DepotController() {
    }

    @RequestMapping({"view"})
    @RequiresPermissions(
            value = {"depot:view", "仓库界面"},
            logical = Logical.OR
    )
    public String view() {
        return "depot";
    }

    @RequestMapping({"list"})
    @ResponseBody
    @RequiresPermissions(
            value = {"depot:list", "仓库列表"},
            logical = Logical.OR
    )
    public PageResult list(QueryObject qo) {
        return this.depotService.query(qo);
    }

    @RequestMapping({"saveOrUpdate"})
    @ResponseBody
    @RequiresPermissions(
            value = {"depot:saveOrUpdate", "仓库增加/更新"},
            logical = Logical.OR
    )
    public JsonResult saveOrUpdate(Depot entity) {
        JsonResult result = new JsonResult();

        try {
            this.depotService.save(entity);
        } catch (Exception var4) {
            result.mark("操作失败");
        }

        return result;
    }

    @RequestMapping({"delete"})
    @ResponseBody
    @RequiresPermissions(
            value = {"depot:delete", "仓库删除"},
            logical = Logical.OR
    )
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();

        try {
            this.depotService.deleteByPrimaryKey(id);
        } catch (Exception var4) {
            result.mark("操作失败");
        }

        return result;
    }

    @RequestMapping({"selectAllEmployee"})
    @ResponseBody
    public List<Employee> selectAllEmployee() throws Exception {
        return this.employeeService.selectAll();
    }

    @RequestMapping({"changeState"})
    @ResponseBody
    @RequiresPermissions(
            value = {"depot:changeState", "仓库停用"},
            logical = Logical.OR
    )
    public JsonResult changeState(Long id) {
        JsonResult result = new JsonResult();

        try {
            this.depotService.changeState(id);
        } catch (Exception var4) {
            result.mark("操作失败");
        }

        return result;
    }
}
