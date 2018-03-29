package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IEmployeeService;
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

    @RequestMapping("removeData")
    @ResponseBody
    public Object dataManage(Long[] ids){
        HttpServletRequest request = UserUtil.getRequest();
        JsonResult result = new JsonResult();
        return result;
    }
}
