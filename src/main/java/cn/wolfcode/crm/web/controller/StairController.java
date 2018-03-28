package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Secondary;
import cn.wolfcode.crm.domain.Stair;
import cn.wolfcode.crm.mapper.StairMapper;
import cn.wolfcode.crm.service.IStairService;
import cn.wolfcode.crm.util.JsonResult;
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
@RequestMapping("stair")
public class StairController {

    @Autowired
    private IStairService stairService;
    @Autowired
    private StairMapper stairMapper;

    @RequestMapping("view")
    public String view(){
        return "stair";
    }

    //商品列表
    @RequestMapping("list")
    @ResponseBody
    public Object list(){

        return stairService.listAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Stair entity){
        JsonResult jsonResult = new JsonResult();
        try {
            stairService.saveOrUpdate(entity);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(Long id){
        JsonResult jsonResult = new JsonResult();
        try {
            stairService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
    //根据一级分类id查询二级商品分类!
    @RequestMapping("getStair")
    @ResponseBody
    public Object getCitys(Long id){

        List<Secondary> stairBySecondaryId = stairService.getStairBySecondaryId(id);

        return stairBySecondaryId;
    }
}
























