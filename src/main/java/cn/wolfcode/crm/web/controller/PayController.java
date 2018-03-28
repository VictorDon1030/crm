package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.service.IPayService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("pay")
public class PayController {

    @Autowired
    private IPayService payService;

    @RequestMapping("view")
    public String view(){
        return "pay";
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(Pay pay){
        JsonResult result=new JsonResult();
        try{

            payService.insert(pay);
        }catch (Exception e){
            result.mark(e.getMessage());
        }
        return  result;
    }
}
