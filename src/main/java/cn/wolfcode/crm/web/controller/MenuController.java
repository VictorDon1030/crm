package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.query.MenuQuery;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMenuService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("menu")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @RequestMapping("getRootMenu")
    @ResponseBody
    public Object getRootMenu(){
        Session session = SecurityUtils.getSubject().getSession();
        Object menus = session.getAttribute("MENUS");
        if (menus == null){
            menus = menuService.getRootMenu();
            menuService.selectCheckMenu((List<Menu>) menus);
            session.setAttribute("MENUS",menus);
        }
        return menus;
    }


    @RequestMapping("list")
    @ResponseBody
    public Object list(MenuQuery mq){
        return menuService.selectMenus(mq);
    }

    @RequestMapping("view")
    @RequiresPermissions(value={"menu:view","菜单列表"},logical = Logical.OR)
    public String view(){
        return "menu";
    }


    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long parentId){
        JsonResult result = new JsonResult();
        try {
            menuService.deleteByPrimaryKey(parentId);
        } catch (Exception e){
            result.mark("亲,删除失败");
        }
        return result;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Menu entity){
        JsonResult result = new JsonResult();
        try {
            if (entity.getId() == null){
                menuService.insert(entity);
            } else {
                menuService.updateByPrimaryKey(entity);
            }
        } catch (Exception e){
            result.mark("亲,保存失败");
        }
        return result;
    }

}
