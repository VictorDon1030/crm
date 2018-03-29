package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.mapper.ClassAnalysisMapper;
import cn.wolfcode.crm.query.ClassAnalysisObject;
import cn.wolfcode.crm.query.PayQueryObject;
import cn.wolfcode.crm.query.ProductAnalyzeObject;
import cn.wolfcode.crm.service.IClassAnalysisService;
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
@RequestMapping("classAnalysis")
public class ClassAnalysisController {

    @Autowired
    private IClassAnalysisService classAnalysisService;

    @RequestMapping("view")
    public String view(Model model, ClassAnalysisObject qo){
        //饼状图，注意饼块里的数据拼接
        //1.根据条件查出数据
        List<Map<String,Object>> result=classAnalysisService.queryByDate(qo);
        //2.拿到所有的分组类型
        List<String> types=new ArrayList<>();//用来存储所有的分组类型:maxType
        List<Map<String,Object>> totalAmounts=new ArrayList<>();//
        for (Map<String, Object> item : result) {
            types.add(item.get("name").toString());
            Map<String,Object> map=new HashMap<>();
            map.put("value",item.get("totalAmount"));
            map.put("name",item.get("name"));
            totalAmounts.add(map);
        }
        model.addAttribute("types", JSON.toJSONString(types));//转换成json格式，共享给页面
        model.addAttribute("totalAmounts", JSON.toJSONString(totalAmounts));

        return "classAnalysis";
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @RequestMapping("queryByDate")
    @ResponseBody
    public Object queryByDate(ProductAnalyzeObject qo){
        return classAnalysisService.queryByDate(qo);
    }
}
