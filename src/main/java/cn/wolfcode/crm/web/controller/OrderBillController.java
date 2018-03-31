package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.OrderBill;
import cn.wolfcode.crm.domain.OrderBillItem;
import cn.wolfcode.crm.mapper.OrderBillMapper;
import cn.wolfcode.crm.query.OrderBillQueryObject;
import cn.wolfcode.crm.service.IOrderBillService;
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
@RequestMapping("orderBill")
public class OrderBillController {
    @Autowired
    private IOrderBillService orderBillService;
    @Autowired
    private OrderBillMapper orderBillMapper;

    @RequestMapping({"view"})
    @RequiresPermissions(
            value = {"orderBill:view", "采购界面"},
            logical = Logical.OR
    )
    public String view() {
        return "orderBill";
    }

    @RequestMapping({"list"})
    @ResponseBody
    @RequiresPermissions(
            value = {"orderBill:list", "采购列表"},
            logical = Logical.OR
    )
    public PageResult list(OrderBillQueryObject qo) {
        return this.orderBillService.query(qo);
    }


    @RequestMapping("input")
    @ResponseBody
    public Object input(Long id)throws Exception{
        if (id!=null){
            return orderBillService.get(id);
        }
        return  new JsonResult();
    }
    //查询所有的已入库单
    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll()throws Exception{
        return orderBillService.selectAll();
    }

    @RequestMapping({"saveOrUpdate"})
    @ResponseBody
    @RequiresPermissions(
            value = {"orderBill:saveOrUpdate", "采购增加/更新"},
            logical = Logical.OR
    )
    public JsonResult saveOrUpdate(OrderBill entity) {
        JsonResult result = new JsonResult();
        try {
            orderBillService.saveOrUpdate(entity);
        } catch (Exception e) {
            result.mark("操作失败");
        }

        return result;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("orderBill:delete")
    public Object delete(Long id)throws Exception{
        orderBillService.delete(id);
        return new JsonResult();
    }
    //审核
    @RequestMapping("audit")
    @ResponseBody

    public Object audit(Long id)throws Exception{
        orderBillService.audit(id);
        return new JsonResult();
    }
    //导出
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response)throws Exception{
        //设置文件响应头
        response.setHeader("Content-Disposition","attachment;filename=orderBill.xls");
        //创建exls文件
        Workbook workbook = new HSSFWorkbook();
        //创建工作薄
        Sheet sheet = workbook.createSheet("day01");
        //设置标题
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("供应商");
        row.createCell(1).setCellValue("单据编号");
        row.createCell(2).setCellValue("产品名称");
        row.createCell(3).setCellValue("单价");
        row.createCell(4).setCellValue("商品总数量");
        row.createCell(5).setCellValue("总金额");
        row.createCell(6).setCellValue("入库状态");
        row.createCell(7).setCellValue("进货时间");
        List<OrderBill> orderBillList = orderBillMapper.selectAll();
        for (int i = 0; i < orderBillList.size(); i++) {
            OrderBill orderBill = orderBillList.get(i);
            List<OrderBillItem> items = orderBill.getItems();
            //创建行
            Row row1 = sheet.createRow(i + 1);
            //设置单元格内容
            if(orderBill.getDepot().getName()!=null){
                row1.createCell(0).setCellValue(orderBill.getDepot().getName());
            }
            if (orderBill.getSn() != null) {
                row1.createCell(1).setCellValue(orderBill.getSn());

            }
            if (items.get(0).getProduct().getName() != null) {

                row1.createCell(2).setCellValue(items.get(0).getProduct().getName());
            }
            if (items.get(0).getCostPrice() != null) {

                row1.createCell(3).setCellValue(String.valueOf(items.get(0).getCostPrice()));
            }
            if (orderBill.getTotalNumber().toString() != null) {

                row1.createCell(4).setCellValue(orderBill.getTotalNumber().toString());
            }
            if (orderBill.getTotalAmount() != null) {

                row1.createCell(5).setCellValue(String.valueOf(orderBill.getTotalAmount()));
            }
            if (orderBill.getStatus() >=0) {

                row1.createCell(6).setCellValue(orderBill.getStatus());
            }
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            if(orderBill.getAuditTime()!=null){
                String format = simpleDateFormat.format(orderBill.getAuditTime());
                row1.createCell(7).setCellValue(format);
            }
        }
        workbook.write(response.getOutputStream());
    }
}
