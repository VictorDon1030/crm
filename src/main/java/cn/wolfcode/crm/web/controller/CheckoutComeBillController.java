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
    /*@ResponseBody*/
    public void saveOrUpdate(CheckoutComeBill checkoutComeBill){

        checkoutComeBillService.saveOrUpdate(checkoutComeBill);
        /*return new JsonResult();*/
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


}
