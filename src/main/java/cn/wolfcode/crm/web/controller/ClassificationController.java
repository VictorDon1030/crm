package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.PageResult;
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
@RequestMapping("classification")
public class ClassificationController {

    @Autowired
    private IProductService productService;

    @RequestMapping("view")
    public String view(){
        return "classification";
    }

    //商品列表
    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductQueryObject qo){

        return productService.query(qo);
    }

    //查询所有列
    @RequestMapping("countId")
    @ResponseBody
    public Object countId(ProductQueryObject qo){

        return productService.listAll();
    }

    //查询所有列
    @RequestMapping("query")
    @ResponseBody
    public Object query(Long id){

        return productService.get(id);
    }
}
