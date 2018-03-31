package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IAllChatService;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IProductService;
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

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("chat")
public class AllChatController {

    @Autowired
    private IAllChatService allChatService;

    @RequestMapping("statirName")
    public String allStatirName(Model model){
        //分类库存
        List<Map<String, Object>> maps = allChatService.selectAllStair();
        List<Map<String,Object>> one = new ArrayList<>();
        for (Map<String, Object> map : maps) {
            Map<String,Object> oneMap = new HashMap<>();
            String name = (String) map.get("statirName");
            Object num = map.get("totalNumber");
            oneMap.put("value",num);
            oneMap.put("name",name);
            one.add(oneMap);
        }
        //销售额/成本价
        List<Map<String, Object>> mapAmount = allChatService.selectAllAmount();
        List<Object> twoName = new ArrayList<>();
        List<Object> twoCost = new ArrayList<>();
        List<Object> twoSale = new ArrayList<>();
        for (Map<String, Object> mapA : mapAmount) {
            twoName.add(mapA.get("statirName"));
            twoCost.add(mapA.get("totalCost"));
            twoSale.add(mapA.get("totalSale"));
        }
        //总销售/总利润
        List<Map<String, Object>> mapProfit = allChatService.selectAllProfit();
        List<Object> month = new ArrayList<>();
        List<Object> monthSale = new ArrayList<>();
        List<Object> profit = new ArrayList<>();
        for (Map<String, Object> mapA : mapProfit) {
            month.add(mapA.get("saleMonth")+"月");
            monthSale.add(mapA.get("totalSale"));
            profit.add(mapA.get("profit"));
        }

        //最多利润
        List<Map<String, Object>> descProfits = allChatService.selectAllProfitDesc();
        List<Object> productName = new ArrayList<>();
        List<Object> totalCost = new ArrayList<>();
        List<Object> totalSale = new ArrayList<>();
        List<Object> totalProfit = new ArrayList<>();
        int i = 0;
        for (Map<String, Object> descProfit : descProfits) {
            productName.add(descProfit.get("productName"));
            totalCost.add(descProfit.get("totalCostAmount"));
            totalSale.add(descProfit.get("totalSaleAmount"));
            totalProfit.add(descProfit.get("profits"));
            i++;
            if (i == 6){
                break;
            }
        }
        //库存
        model.addAttribute("one", JSON.toJSONString(one));

        //销售支出
        model.addAttribute("twoName", JSON.toJSONString(twoName));
        model.addAttribute("twoCost", JSON.toJSONString(twoCost));
        model.addAttribute("twoSale", JSON.toJSONString(twoSale));

        //利润
        model.addAttribute("month", JSON.toJSONString(month));
        model.addAttribute("monthSale", JSON.toJSONString(monthSale));
        model.addAttribute("profit", JSON.toJSONString(profit));

        //最高商品利润
        model.addAttribute("productName", JSON.toJSONString(productName));
        model.addAttribute("totalCost", JSON.toJSONString(totalCost));
        model.addAttribute("totalSale", JSON.toJSONString(totalSale));
        model.addAttribute("totalProfit", JSON.toJSONString(totalProfit));
        return "forward:/allchart.do";
    }
}
