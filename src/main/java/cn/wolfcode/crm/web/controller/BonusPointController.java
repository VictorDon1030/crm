package cn.wolfcode.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BonusPointController {
    @RequestMapping("bonusPointManage")
    public String bonusPointManage() throws Exception {
        return "bonusPoint";
    }
}
