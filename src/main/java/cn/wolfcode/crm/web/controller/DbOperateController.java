package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.util.DbOperate;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Administrator
 * @date 2018/3/31 20:26
 */
@Controller
@RequestMapping("DbOperate")
public class DbOperateController {
    //数据备份
    @RequestMapping("dbBackup")
    @ResponseBody
    public JsonResult dbBackup(){
        JsonResult jsonResult = new JsonResult();
        try {
            DbOperate.dbBackup("root", "admin", "localhost","3306", "dece", "C:/dece.sql");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonResult;
    }


    //数据还原
    @RequestMapping("dbRecover")
    @ResponseBody
    public JsonResult dbRecover(){
        JsonResult jsonResult = new JsonResult();
        try {
            DbOperate.dbRecover("root", "admin", "localhost","3306", "dece", "C:/dece.sql");

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonResult;
    }

}
