package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class DataController {

    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IProductService productService;

    @RequestMapping("removeData")
    @ResponseBody
    public Object dataManage(Long[] ids){
        JsonResult result = new JsonResult();
        if (ids == null){
            result.mark("没有参数");
            throw new RuntimeException("没有参数");
        }
        try{
            for (Long id : ids) {
                if (id == 1){
                    employeeService.deleteAll();
                } else if (id == 2){
                    productService.deleteAll();
                } else {
                    result.mark("正在开发中");
                }
            }
        } catch (Exception e){
            result.mark(e.getMessage());
        }
        return result;
    }
}
