package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.query.ProductQueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
@RequestMapping("product")
public class ProductController {

    @Autowired
    private IProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @RequestMapping("view")
    public String view(){
        return "product";
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

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Product entity){
        JsonResult jsonResult = new JsonResult();
        try {
            productService.saveOrUpdate(entity);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }
    @RequestMapping("save")
    @ResponseBody
    public Object save(Product entity){
        JsonResult jsonResult = new JsonResult();
        try {
            productService.save(entity);
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
            productService.delete(id);
        }catch (Exception e){
            e.printStackTrace();
            jsonResult.mark("操作失败");
        }
        return jsonResult;
    }

    //导出功能
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response)throws Exception{
        //设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=product.xls");
        //创建excel
        Workbook wb = new HSSFWorkbook();
        //创建工作薄
        Sheet sheet = wb.createSheet("day01");
        //设置标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("用户名");
        row.createCell(1).setCellValue("真实姓名");
        row.createCell(2).setCellValue("邮件");
        row.createCell(3).setCellValue("电话");
        List<Product> productList = productService.listAll();
        for (int i = 1; i < productList.size(); i++) {
            //不-1 会拿不到第一行 因为第一行从0 开始
            Product product = productList.get(i-1);
            //创建行 不能+1
            row = sheet.createRow(i);
            //创建单元格
            // Cell cell = row.createCell(2);
            //设置单元格内容
            //cell.setCellValue("你好");
            row.createCell(0).setCellValue(product.getName());
            row.createCell(1).setCellValue(product.getBrand());
            row.createCell(2).setCellValue(product.getAuxiliaryWord());
            row.createCell(3).setCellValue(product.getGoodsMark());
            row.createCell(4).setCellValue(product.getPurchasingPrice().toString());
            row.createCell(5).setCellValue(product.getUnitpPrice().toString());
            row.createCell(6).setCellValue(product.getMemberPrice().toString());
            row.createCell(7).setCellValue(product.getMinDiscount().toString());
            row.createCell(8).setCellValue(product.getMinPrice().toString());
            row.createCell(9).setCellValue(product.getInitialInventory());
            row.createCell(10).setCellValue(product.getSpecification());
            row.createCell(11).setCellValue(product.getUnit());
            row.createCell(12).setCellValue(product.getIntegral());
            row.createCell(13).setCellValue(product.getCommission().toString());
            row.createCell(14).setCellValue(product.getPastDueTime());
            row.createCell(15).setCellValue(product.getRemark());
            row.createCell(16).setCellValue(product.getImagePath());
        }
        //写入数据(输出到浏览器)
        wb.write(response.getOutputStream());
    }

   /* //导入
    @RequestMapping("importXls")
    public void importXls(MultipartFile file, HttpServletResponse response) throws Exception{
        InputStream inputStream = file.getInputStream();
        //创建excel文件 指定输入流
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);

        Sheet sheet = wb.getSheetAt(0);
        //获取最后一个索引
        int lastRowNum = sheet.getLastRowNum();
        for (int i = 1; i <= lastRowNum; i++) {
            Product product = new Product();
            Row row = sheet.getRow(i);
            try {
                product.setName(getContent(row.getCell(0)).toString());
                product.setBrand(getContent(row.getCell(1)).toString());
                product.setAuxiliaryWord(getContent(row.getCell(2)).toString());
                product.setGoodsMark(getContent(row.getCell(3)).toString());
                product.setPurchasingPrice(getContent(row.getCell(3)).toString());
                product.setUnitpPrice(getContent(row.getCell(3)).toString());
                product.setMemberPrice(getContent(row.getCell(3)).toString());
                product.setMinDiscount(getContent(row.getCell(3)).toString());
                product.setMinPrice(getContent(row.getCell(3)).toString());
                product.setInitialInventory(getContent(row.getCell(3)).toString());
                product.setSpecification(getContent(row.getCell(3)).toString());
                product.setUnit(getContent(row.getCell(3)).toString());
                product.setIntegral(getContent(row.getCell(3)).toString());
                product.setCommission(getContent(row.getCell(3)).toString());
                product.setPastDueTime(getContent(row.getCell(3)).toString());
                product.setRemark(getContent(row.getCell(3)).toString());
                product.setImagePath(getContent(row.getCell(3)).toString());
                productService.saveOrUpdate(product);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public Object getContent(Cell cell){
        switch (cell.getCellType()){
            case 1:
                return cell.getRichStringCellValue().getString();
            case 0:
                return cell.getNumericCellValue();
            default:
        }
        return null;
    }*/

    //查询分类商品列
    @RequestMapping("fruits")
    @ResponseBody
    public Object fruits(Long id){
        return productService.selectByUnitId(1L);
    }
    @RequestMapping("drinks")
    @ResponseBody
    public Object drinks(Long id){
        return productService.selectByUnitId(2L);
    }
    @RequestMapping("foodstuff")
    @ResponseBody
    public Object foodstuff(Long id){
        return productService.selectByUnitId(3L);
    }
    @RequestMapping("vegetables")
    @ResponseBody
    public Object vegetables(Long id){
        return productService.selectByUnitId(4L);
    }
    @RequestMapping("grain")
    @ResponseBody
    public Object grain(Long id){
        return productService.selectByUnitId(5L);
    }
    @RequestMapping("aquatic")
    @ResponseBody
    public Object aquatic(Long id){
        return productService.selectByUnitId(6L);
    }
    @RequestMapping("child")
    @ResponseBody
    public Object child(Long id){
        return productService.selectByUnitId(7L);
    }

}
























