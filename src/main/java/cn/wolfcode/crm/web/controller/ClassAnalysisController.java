package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.mapper.ClassAnalysisMapper;
import cn.wolfcode.crm.query.ClassAnalysisObject;
import cn.wolfcode.crm.query.PayQueryObject;
import cn.wolfcode.crm.query.ProductAnalyzeObject;
import cn.wolfcode.crm.service.IClassAnalysisService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("classAnalysis")
public class ClassAnalysisController {

    @Autowired
    private IClassAnalysisService classAnalysisService;
    private  List<Map<String,Object>> maps;

    @RequestMapping("view")
    public String view(Model model, ClassAnalysisObject qo){
         maps=classAnalysisService.selectAll();

        return "classAnalysis";
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @RequestMapping("queryByDate")
    @ResponseBody
    public Object queryByDate(ProductAnalyzeObject qo){
        maps=classAnalysisService.queryByDate(qo);
        return maps;
    }



    /***
     * 导出报表：不同的查询条件，导出不同的报表：怎么拿到这个查询条件呢？
     * @param response
     * @throws IOException
     */
    @RequestMapping("exportXls")
    public void exportXls(HttpServletResponse response) throws IOException {
        //设置文件下载响应头
        response.setHeader("Content-Disposition","attachment;filename=classAnalysis.xls");
        //创建excel文件
        Workbook wb=new HSSFWorkbook();
        //创建工作簿
        Sheet sheet=wb.createSheet("day01");
        //设置标题:索引从0开始
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("产品分类");
        row.createCell(1).setCellValue("销售数量");
        row.createCell(2).setCellValue("销售金额");
        row.createCell(3).setCellValue("所占比例");
        for(int i=0;i<maps.size();i++){
            //拿到每一个map：一条数据
            Map<String,Object> map=maps.get(i);
            //创建行
            row=sheet.createRow(i+1);
            //设置单元格内容
            row.createCell(0).setCellValue((String)map.get("name"));
            if(map.get("totalNumber")==null){
                row.createCell(1).setCellValue(0);
            }else {

                row.createCell(1).setCellValue((map.get("totalNumber")).toString());
            }
            if(map.get("accountFor")==null){
                row.createCell(2).setCellValue(0);
            }else {

                row.createCell(2).setCellValue((map.get("totalAmount")).toString());
            }
            if(map.get("accountFor")==null){
                row.createCell(3).setCellValue("0.00%");
            }else{

                row.createCell(3).setCellValue((map.get("accountFor")).toString());
            }
        }
        //写入数据，输出到浏览器
        wb.write(response.getOutputStream());
    }

    /***
     * 饼状图的数据
     */
    @RequestMapping("queryForPie")
    @ResponseBody
    public Object queryForPie(ClassAnalysisObject qo){
        //1.根据条件查出数据
        List<Map<String,Object>> result=classAnalysisService.queryByDate(qo);
        //2.要将分组类型及对应的amount
        List<Map<String,Object>> list=new ArrayList<>();//
        for (Map<String, Object> item : result) {
            Map<String,Object> map=new HashMap<>();
            map.put("typeName",item.get("name"));
            map.put("totalAmount",item.get("totalAmount"));
            list.add(map);
        }
        return list;
    }

}
