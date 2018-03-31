package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.query.PayQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPayService;
import cn.wolfcode.crm.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("pay")
public class PayController {

    @Autowired
    private IPayService payService;

    @RequestMapping("view")
    public String view(Model model,PayQueryObject qo){

        //时间轴
        List<Map<String,Object>> times=payService.selectBeforeFiveByDate();
        System.out.print("------------"+times.size()+"------------");
        model.addAttribute("times",times);
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

    /**
     * 根据日期查询
     */
    @RequestMapping("selectByDate")
    @ResponseBody
    public Object selectByDate(Model model,PayQueryObject qo){
        //1.根据条件查出数据
        List<Map<String,Object>> result=payService.query(qo);
        //返回给datagrid
        return result;
    }

    /***
     * 饼状图的数据
     */
    @RequestMapping("queryForPie")
    @ResponseBody
    public Object queryForPie(PayQueryObject qo){
        //1.根据条件查出数据
        List<Map<String,Object>> result=payService.query(qo);
        //2.要将分组类型及对应的amount
        List<Map<String,Object>> list=new ArrayList<>();//
        for (Map<String, Object> item : result) {
            Map<String,Object> map=new HashMap<>();
            map.put("typeName",item.get("maxType"));
            map.put("totalAmount",item.get("amount"));
            list.add(map);
        }
        return list;
    }

}
