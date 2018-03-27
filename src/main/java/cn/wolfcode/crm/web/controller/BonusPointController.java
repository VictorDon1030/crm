package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接收页面参数,并实现对会员积分管理页面的跳转控制
 * @author 应举
 *
 */
@Controller
@RequestMapping("bonusPoint")
public class BonusPointController {
    @RequestMapping("bonusPointManage")
    public String bonusPointManage() throws Exception {
        return "bonusPoint";
    }
}
