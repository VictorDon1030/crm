package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import cn.wolfcode.crm.service.ICheckoutComeBillService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("checkoutComeBill")
public class CheckoutComeBillController {
    @Autowired
    private ICheckoutComeBillService checkoutComeBillService;
    /*@Autowired
    private IDepotService depotService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IClientService clientService;*/


    @RequestMapping("saveOrUpdate")
    @ResponseBody
    //用于支付
    public Object saveOrUpdate(CheckoutComeBill checkoutComeBill){
        JsonResult result = new JsonResult();
        try{
            checkoutComeBillService.saveOrUpdate(checkoutComeBill);
        }catch (Exception e){
           result.mark("支付失败!"+e.getMessage());
        }
        return result;
    }
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        if (id!=null){
            checkoutComeBillService.deleteByPrimaryKey(id);
        }
        return new JsonResult();
    }

    /*@RequestMapping("audit")
    @ResponseBody
    public Object audit(Long id){
        FastJsonUtil util = new FastJsonUtil();
        try{
            checkoutComeBillService.audit(id);
        }catch (ProductExceptions e){
            e.printStackTrace();
            util.mark(e.getMessage());
        }

        return util;
    }*/
    @RequestMapping("waitPayment")
    //挂单
    @ResponseBody
    public Object waitPayment(CheckoutComeBill checkoutComeBill){
        JsonResult result = new JsonResult();
        try{
            checkoutComeBillService.waitPayment(checkoutComeBill);
        }catch (Exception e){
            result.mark("挂单失败!"+e.getMessage());
        }
        return result;
    }

    @RequestMapping("selectwaitPayment")
    @ResponseBody
    //查找挂单会员
    public Object selectwaitPayment(Long id){
        return checkoutComeBillService.selectWaitPaymentMember();
    }

    @RequestMapping("selectbyMemberId")
    @ResponseBody
    public Object selectbyMemberId(Long id){
        return checkoutComeBillService.selectbyMemberId(id);
    }

    @RequestMapping("addOdd")
    @ResponseBody
    //设置单号
    public Object addOdd(String sn){
        return String.valueOf(checkoutComeBillService.addOdd(sn));
    }
}
