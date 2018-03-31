package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Linkage;
import cn.wolfcode.crm.service.ILinkageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
@Controller
@RequestMapping("linkage")
public class LinkageController {
    @Autowired
    private ILinkageService linkageService;

    @RequestMapping("view")
    public String index(){

        return "linkage";
    }

    @RequestMapping("getRootLinkage") //树需要用
    @ResponseBody
    public Object getRootLinkage(){
        //很少用 所以使用缓存
        Session session = SecurityUtils.getSubject().getSession();
        Object linkages = session.getAttribute("LINKAGES");
        //判断session中是否有缓存起来的菜单 如果有就直接返回 没有就去查询 再放入session
        if(linkages == null){
            //查询出父菜单和子菜单
            linkages = linkageService.getRootLinkage();
            //先做权限过滤 再返回给页面
            linkageService.selectPermission((List<Linkage>) linkages);
            session.setAttribute("LINKAGES", linkages);
        }
        return linkages;
    }

    @RequestMapping("query")
    public String query(Model model)throws Exception{
        model.addAttribute("result", linkageService.list());
        return "linkage/list";
    }
    @RequestMapping("input")
    public String input(Model model,Long id){
        if (id != null) {
            Linkage entity = linkageService.get(id);
            model.addAttribute("entity",entity);
        }
        return "linkage/input";
    }

/*    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOfUpdate(Linkage linkage){
        linkageService.saveOrUpdate(linkage);
        return new JSONResult();
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        linkageService.delete(id);
        return new JSONResult();
    }*/

}




































