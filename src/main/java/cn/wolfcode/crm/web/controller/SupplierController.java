package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISupplierService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Administrator
 * @date 2018/3/27 23:31
 */
@Controller
@RequestMapping({"supplier"})
public class SupplierController {
    @Autowired
    private ISupplierService supplierService;

    public SupplierController() {
    }

    @RequestMapping({"view"})
    @RequiresPermissions(
            value = {"supplier:view", "供应商界面"},
            logical = Logical.OR
    )
    public String view() {
        return "supplier";
    }

    @RequestMapping({"list"})
    @ResponseBody
    @RequiresPermissions(
            value = {"supplier:list", "供应商列表"},
            logical = Logical.OR
    )
    public PageResult list(QueryObject qo) {
        return this.supplierService.query(qo);
    }

    @RequestMapping({"saveOrUpdate"})
    @ResponseBody
    @RequiresPermissions(
            value = {"supplier:saveOrUpdate", "供应商增加/更新"},
            logical = Logical.OR
    )
    public JsonResult saveOrUpdate(Supplier entity) {
        JsonResult result = new JsonResult();

        try {
            this.supplierService.save(entity);
        } catch (Exception var4) {
            result.mark("操作失败");
        }

        return result;
    }

    @RequestMapping({"delete"})
    @ResponseBody
    @RequiresPermissions(
            value = {"supplier:delete", "供应商删除"},
            logical = Logical.OR
    )
    public JsonResult delete(Long id) {
        JsonResult result = new JsonResult();

        try {
            this.supplierService.deleteByPrimaryKey(id);
        } catch (Exception var4) {
            result.mark("操作失败");
        }

        return result;
    }
}
