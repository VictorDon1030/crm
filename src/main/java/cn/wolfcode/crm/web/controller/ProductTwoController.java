package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ProductTwo;
import cn.wolfcode.crm.mapper.ProductTwoMapper;
import cn.wolfcode.crm.service.IProductTwoService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("producttwo")
public class ProductTwoController {

    @Autowired
    private IProductTwoService productTwoService;
    @Autowired
    private ProductTwoMapper productTwoMapper;

    @RequestMapping("view")
    public String view(){
        return "productTwo";
    }

    //分类商品列表     下架 商品
    @RequestMapping("list")
    @ResponseBody
    public Object list(){

        return productTwoService.listAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(ProductTwo entity){
        JsonResult jsonResult = new JsonResult();
        try {
            productTwoService.saveOrUpdate(entity);
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
            productTwoService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
}
























