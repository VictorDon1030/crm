package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.SaleAnalyzeObject;
import cn.wolfcode.crm.service.ISaleAnalyzeService;
import com.alibaba.fastjson.JSON;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("saleAnalyze")
public class SaleAnalyzeController {

    @Autowired
    private ISaleAnalyzeService saleAnalyzeService;
    private List<Map<String,Object>> maps;

    @RequestMapping("view")
    public String view(Model model){
        maps=saleAnalyzeService.selectAll();
        return "saleAnalyze";
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @RequestMapping("queryByDate")
    @ResponseBody
    public Object queryByDate(SaleAnalyzeObject qo){
        maps=saleAnalyzeService.queryByDate(qo);
        return maps;
    }

    /***
     * 导出报表：不同的查询条件，导出不同的报表：怎么拿到这个查询条件呢？
     * @param response
     * @param qo
     * @throws IOException
     */
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response, SaleAnalyzeObject qo) throws IOException {
        //设置文件下载响应头
        response.setHeader("Content-Disposition","attachment;filename=saleAnalyze.xls");
        //创建excel文件
        Workbook wb=new HSSFWorkbook();
        //创建工作簿
        Sheet sheet=wb.createSheet("day01");
        //设置标题:索引从0开始
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("商品名稱");
        row.createCell(1).setCellValue("单号");
        row.createCell(2).setCellValue("消费对象");
        row.createCell(3).setCellValue("商品单价");
        row.createCell(4).setCellValue("折扣类型");
        row.createCell(5).setCellValue("数量");
        row.createCell(5).setCellValue("销售金额");
        row.createCell(5).setCellValue("支付方式");
        row.createCell(5).setCellValue("消费店铺");
        row.createCell(5).setCellValue("消费时间");
        for(int i=0;i<maps.size();i++){
            //拿到每一个map：一条数据
            Map<String,Object> map=maps.get(i);
            //创建行
            row=sheet.createRow(i+1);
            //设置单元格内容
            row.createCell(0).setCellValue((String)map.get("name"));
            if(map.get("sn")==null){
                row.createCell(1).setCellValue("");
            }else{

                row.createCell(1).setCellValue((String)map.get("sn"));
            }
            if(map.get("member")==null){
                row.createCell(2).setCellValue("散客");
            }else{

                row.createCell(2).setCellValue("会员");
            }
            row.createCell(3).setCellValue((map.get("salePrice")).toString());
            row.createCell(4).setCellValue("无折扣");
            row.createCell(5).setCellValue((map.get("number")).toString());
            row.createCell(6).setCellValue((map.get("saleAmount")).toString());
            row.createCell(7).setCellValue("现金");
            row.createCell(8).setCellValue("德客便利店");
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            row.createCell(9).setCellValue(format.format(map.get("vdate")));
        }
        //写入数据，输出到浏览器
        wb.write(response.getOutputStream());
    }
}
