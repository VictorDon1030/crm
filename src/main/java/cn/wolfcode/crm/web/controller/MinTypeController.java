package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.MinType;
import cn.wolfcode.crm.service.IMinTypeService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("minType")
public class MinTypeController {
    @Autowired
    private IMinTypeService minTypeService;

    /**
     * 根据支出大分类的id查出其所有的支出小分类
     * @param maxTypeId 支出大分类的id
     * @return 所有的支出小分类集合
     */
    @RequestMapping("selectByMaxTypeId")
    @ResponseBody
    public Object selectByMaxTypeId(Long maxTypeId){
        return minTypeService.selectByMaxTypeId(maxTypeId);
    }

    /**
     * 插入小分类
     */
    @RequestMapping("insertMinType")
    @ResponseBody
    public Object insertMinType(MinType minType){
        JsonResult result=new JsonResult();
        try{

            minTypeService.insert(minType);
        }catch (Exception e){
            result.mark("添加失败，当前大分类已含有此小分类！");
        }
        return  result;
    }

    /**
     * 删除
     */
    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long minTypeId){
        JsonResult result=new JsonResult();
        try{

            minTypeService.delete(minTypeId);
        }catch (Exception e){
            result.mark(e.getMessage());
        }
        return  result;
    }
}
