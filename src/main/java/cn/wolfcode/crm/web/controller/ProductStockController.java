package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.query.InventoryQueryObject;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.service.IProductStockService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PageResult;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    public String view() {
        return "productStock";
    }
    //盘点页面
    @RequestMapping("inventory")
    public String inventory() {
        return "inventory";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageResult list(ProductStockQueryObject qo) throws Exception {

        return productStockService.query(qo);
    }
    @RequestMapping("query")
    @ResponseBody
    public Object query()throws Exception{

        return productStockService.selectAll();
    }
    @RequestMapping("listNoaudit")
    @ResponseBody
    public PageResult listNoaudit(ProductStockQueryObject qo) throws Exception {

        return productStockService.queryNoaudit(qo);
    }

    //通过仓库id查询库存
    @RequestMapping("selectProductStockByDepotId")
    @ResponseBody
    public PageResult selectProductStockByDepotId(ProductStockQueryObject qo) throws Exception {

        return productStockService.selectProductStockByDepotId(qo);
    }

    //盘点确认
    @RequestMapping({"inventoryAffirm"})
    @ResponseBody
    @RequiresPermissions(
            value = {"productStock:inventoryAffirm", "盘点确认"},
            logical = Logical.OR
    )
    public JsonResult inventoryAffirm(ProductStock entity) {
        JsonResult result = new JsonResult();
        try {
            productStockService.updateInventoryTime(entity);
        } catch (Exception var4) {
            result.mark("操作失败");
        }
        return result;
    }
    //盘点数量调整
    @RequestMapping({"inventoryNewNumber"})
    @ResponseBody
    @RequiresPermissions(
            value = {"productStock:inventoryNewNumber", "盘点数量调整"},
            logical = Logical.OR
    )
    public JsonResult inventoryNewNumber(ProductStock entity) {
        JsonResult result = new JsonResult();
        try {
            productStockService.updateStoreNumber(entity);
        } catch (Exception var4) {
            result.mark("操作失败");
        }
        return result;
    }
    //盘点记录
    @RequestMapping({"listAll"})
    @ResponseBody
    public PageResult listAll(InventoryQueryObject qo) {
        return productStockService.listAll(qo);
    }

    //导出库存报表
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) throws Exception {
        //设置文件响应头
        response.setHeader("Content-Disposition", "attachment;filename=productStock.xls");
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
        List<ProductStock> productStockList = productStockService.selectAll();
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
            if (productStock.getAuditTime() != null) {
                String format = simpleDateFormat.format(productStock.getAuditTime());
                row1.createCell(4).setCellValue(format);

            }
            if (productStock.getProduct().getPastDueTime() != null) {
                String format = simpleDateFormat.format(productStock.getProduct().getPastDueTime());
                row1.createCell(5).setCellValue(format);
            }
        }
        workbook.write(response.getOutputStream());
    }
    //导出盘点报表
    @RequestMapping("exportInventoryXls")
    public void exportInventoryXls(HttpServletResponse response) throws Exception {
        //设置文件响应头
        response.setHeader("Content-Disposition", "attachment;filename=inventoryItem.xls");
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
        row.createCell(4).setCellValue("规格");
        row.createCell(5).setCellValue("入库时间");
        row.createCell(6).setCellValue("过期时间");
        row.createCell(7).setCellValue("上次盘点时间");
        List<ProductStock> productStockList = productStockService.selectAll();
        for (int i = 0; i < productStockList.size(); i++) {
            ProductStock productStock = productStockList.get(i);
            //创建行
            Row row1 = sheet.createRow(i + 1);
            //设置单元格内容
            row1.createCell(0).setCellValue(productStock.getDepot().getName());
            row1.createCell(1).setCellValue(productStock.getProduct().getGoodsMark());
            row1.createCell(2).setCellValue(productStock.getProduct().getName());
            row1.createCell(3).setCellValue(String.valueOf(productStock.getStoreNumber()));
            row1.createCell(4).setCellValue(String.valueOf(productStock.getProduct().getSpecification()));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if (productStock.getAuditTime() != null) {
                String format = simpleDateFormat.format(productStock.getAuditTime());
                row1.createCell(5).setCellValue(format);

            }
            if (productStock.getProduct().getPastDueTime() != null) {
                String format = simpleDateFormat.format(productStock.getProduct().getPastDueTime());
                row1.createCell(6).setCellValue(format);
            }
            if (productStock.getInventoryTime() != null) {
                String format = simpleDateFormat.format(productStock.getInventoryTime());
                row1.createCell(7).setCellValue(format);
            }
        }
        workbook.write(response.getOutputStream());
    }
}
