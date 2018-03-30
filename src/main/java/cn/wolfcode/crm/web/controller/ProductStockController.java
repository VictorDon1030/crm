package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.service.IProductStockService;
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
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("productStock")
public class ProductStockController {
    @Autowired
    private IProductStockService productStockService;

    @RequestMapping("view")
    public String view(){
        return "productStock";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductStockQueryObject qo)throws Exception{

        return productStockService.query(qo);
    }
    @RequestMapping("query")
    @ResponseBody
    public Object query()throws Exception{

        return productStockService.selectAll();
    }
    @RequestMapping("selectProductStockByDepotId")
    @ResponseBody
    public PageResult selectProductStockByDepotId(ProductStockQueryObject qo)throws Exception{

        return productStockService.selectProductStockByDepotId(qo);
    }

    //导出
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response)throws Exception{
        //设置文件响应头
        response.setHeader("Content-Disposition","attachment;filename=productStock.xls");
        //创建exls文件
        Workbook workbook = new HSSFWorkbook();
        //创建工作薄
        Sheet sheet = workbook.createSheet("day01");
        //设置标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("仓库名称");
        row.createCell(1).setCellValue("商品编号");
        row.createCell(2).setCellValue("商品名称");
        row.createCell(3).setCellValue("库存数量");
        row.createCell(4).setCellValue("入库时间");
        row.createCell(5).setCellValue("过期时间");
        List<ProductStock> productStockList =productStockService .selectAll();
        for (int i = 0; i < productStockList.size(); i++) {
            ProductStock productStock = productStockList.get(i);
            //创建行
            Row row1 = sheet.createRow(i + 1);
            //设置单元格内容
            row1.createCell(0).setCellValue(productStock.getDepot().getName());
            row1.createCell(1).setCellValue(productStock.getProduct().getGoodsMark());
            row1.createCell(2).setCellValue(productStock.getProduct().getName());
            row1.createCell(3).setCellValue(String.valueOf(productStock.getStoreNumber()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (productStock.getAuditTime()!=null){
                String format = simpleDateFormat.format(productStock.getAuditTime());
                row1.createCell(4).setCellValue(format);

            }
            if (productStock.getProduct().getPastDueTime()!=null){
                String format = simpleDateFormat.format(productStock.getProduct().getPastDueTime());
                row1.createCell(5).setCellValue(format);
            }
        }
        workbook.write(response.getOutputStream());
    }
}
