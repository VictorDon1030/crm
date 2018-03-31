package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ProductOne;
import cn.wolfcode.crm.mapper.ProductOneMapper;
import cn.wolfcode.crm.service.IProductOneService;
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
@RequestMapping("productone")
public class ProductOneController {

    @Autowired
    private IProductOneService productOneService;
    @Autowired
    private ProductOneMapper productOneMapper;

    @RequestMapping("view")
    public String view(){
        return "productOne";
    }

    //分类商品列表   上架商品
    @RequestMapping("list")
    @ResponseBody
    public Object list(){

        return productOneService.listAll();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(ProductOne entity){
        JsonResult jsonResult = new JsonResult();
        try {
            productOneService.saveOrUpdate(entity);
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
            productOneService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
}
























