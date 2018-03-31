package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SubBranch;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SubBranchQuery;
import cn.wolfcode.crm.service.ISubBranchService;
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
@RequestMapping("subBranch")
public class SubBranchController {

    @Autowired
    ISubBranchService subBranchService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return subBranchService.selectAll();
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"subBranch:view","分店列表"},logical = Logical.OR)
    public String view(){
        return "subBranch";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(SubBranchQuery qo){
        return subBranchService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            subBranchService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SubBranch entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                entity.setState(true);
                entity.setMallState(true);
                subBranchService.insert(entity);
            } else {
                subBranchService.updateByPrimaryKey(entity);
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
            subBranchService.changeState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }

    @RequestMapping("changeMallState")
    @ResponseBody
    public Object changeMallState(Long id){
        JsonResult result = new JsonResult();
        try {
            subBranchService.changeMallState(id);
        } catch (Exception e){
            result.mark("设置失败");
        }
        return result;
    }
}
