package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SaleasCount;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISaleasCountService;
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
@RequestMapping("saleasCount")
public class SaleasCountController {

    @Autowired
    ISaleasCountService saleasCountService;

    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return saleasCountService.selectAll();
    }

    @RequestMapping("view")
    public String view(){
        return "saleasCount";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(QueryObject qo){
        return saleasCountService.query(qo);
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult result = new JsonResult();
        try {
            saleasCountService.deleteByPrimaryKey(id);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(SaleasCount entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                saleasCountService.insert(entity);
            } else {
                saleasCountService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }
        return result;
    }
}
