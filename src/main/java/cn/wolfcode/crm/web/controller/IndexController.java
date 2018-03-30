package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.LoginLog;
import cn.wolfcode.crm.mapper.LoginLogMapper;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class IndexController {

    @Autowired
    private LoginLogMapper loginLogMapper;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("login")
    public String login(){

        return "redirect:/login.jsp";
    }

    //跳转主页面
    @RequestMapping("main")
    public String main(){
        //这里暂时还是报错的,index主页面跳转路径更改即可解决
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        LoginLog loginLog = new LoginLog();
        loginLog.setLogTime(new Date());
        loginLog.setLogName(employee.getUsername());
        loginLog.setLogIp(request.getRemoteAddr());
        loginLogMapper.insert(loginLog);
        return "main";
    }

    //跳转系统设置页面
    @RequestMapping("syshome")
    public String syshome(){

        return "syshome";
    }

    //跳转我的店铺
    @RequestMapping("myshop")
    public String myhsop(){

        return "myshop";
    }

    //跳转管理主页面
    @RequestMapping("management")
    public String management(){

        return "management";
    }

    //跳转数据管理
    @RequestMapping("dataManage")
    public String dataManage(){

        return "dataManage";
    }

    //跳转
    @RequestMapping("payment")
    public String payment(){

        return "payment";
    }
}
