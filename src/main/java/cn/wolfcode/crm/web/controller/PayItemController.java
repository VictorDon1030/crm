package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.query.PayItemQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPayItemService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("payItem")
public class PayItemController {

    @Autowired
    private IPayItemService payItemService;
    //导出报表：默认是导出所有
    private List<PayItem> payItemList;
    @RequestMapping("view")
    public String view(){
        payItemList=payItemService.selectAll();
        return "payItem";
    }
    /**
     * 查询所有的支出明细
     * @return
     */
    @RequestMapping("selectAll")
    @ResponseBody
    public Object selectAll(){
        return payItemList;
    }

    /**
     * 高级查询：多条件、分页
     */
    @RequestMapping("list")
    @ResponseBody
    public Object list(PayItemQueryObject qo){

        //作用：不同的查询条件，导出不同的报表
        payItemList= (List<PayItem>) payItemService.query(qo).getRows();
        return payItemService.query(qo);
    }

    /**
     * 导出支出报表
     */
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) throws IOException {
        //设置文件下载响应头
        response.setHeader("Content-Disposition","attachment;filename=payItem.xls");
        //创建excel文件
        Workbook wb=new HSSFWorkbook();
        //创建工作簿
        Sheet sheet=wb.createSheet("day01");
        //设置标题:索引从0开始
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("支出分类");
        row.createCell(1).setCellValue("支出金额");
        row.createCell(2).setCellValue("操作时间");
        row.createCell(3).setCellValue("支出人员");
        row.createCell(4).setCellValue("备注说明");
        for(int i=0;i<payItemList.size();i++){
            PayItem payItem=payItemList.get(i);
            //创建行
            row=sheet.createRow(i+1);
            //设置单元格内容
            row.createCell(0).setCellValue(payItem.getType());
            row.createCell(1).setCellValue(payItem.getAmount());
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            row.createCell(2).setCellValue(format.format(payItem.getDate()));
            row.createCell(3).setCellValue(payItem.getPayUser().getUsername());
            row.createCell(4).setCellValue(payItem.getRemark());
        }
        //写入数据，输出到浏览器
        wb.write(response.getOutputStream());
    }
}
