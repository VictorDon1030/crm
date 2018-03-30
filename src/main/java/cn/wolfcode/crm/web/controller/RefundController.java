package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.Refund;
import cn.wolfcode.crm.query.RefundQueryObject;
import cn.wolfcode.crm.service.IRefundService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("refund")
public class RefundController {
    @Autowired
    private IRefundService refundService;


    @RequestMapping({"view"})
    @RequiresPermissions(
            value = {"refund:view", "采购界面"},
            logical = Logical.OR
    )
    public String view() {
        return "refund";
    }

    @RequestMapping({"list"})
    @ResponseBody
    @RequiresPermissions(
            value = {"refund:list", "采购列表"},
            logical = Logical.OR
    )
    public PageResult list(RefundQueryObject qo) {
        return this.refundService.query(qo);
    }


    @RequestMapping("input")
    @ResponseBody
    public Object input(Long id)throws Exception{
        if (id!=null){
            return refundService.get(id);
        }
        return  new JsonResult();
    }
    @RequestMapping({"saveOrUpdate"})
    @ResponseBody
    @RequiresPermissions(
            value = {"refund:saveOrUpdate", "采购增加/更新"},
            logical = Logical.OR
    )
    public JsonResult saveOrUpdate(Refund entity) {
        JsonResult result = new JsonResult();
        try {
            refundService.saveOrUpdate(entity);
        } catch (Exception e) {
            result.mark("操作失败");
        }

        return result;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("refund:delete")
    public Object delete(Long id)throws Exception{
        refundService.delete(id);
        return new JsonResult();
    }
    //审核
    @RequestMapping("audit")
    @ResponseBody

    public Object audit(Long id)throws Exception{
        refundService.audit(id);
        return new JsonResult();
    }

}
