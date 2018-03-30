package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.MemberAnalyzeObject;
import cn.wolfcode.crm.service.IMemberAnalyzeService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("memberAnalyze")
public class MemberAnalyzeController {

    @Autowired
    private IMemberAnalyzeService memberAnalyzeService;
    private List<Map<String,Object>> maps=memberAnalyzeService.selectAll();

    @RequestMapping("view")
    public String view(Model model){
        //柱状图需要：所有的分组类型，及对应分组类型的销售总额
        //1.根据多条件所有的数据:排序
        List<Map<String,Object>> result=memberAnalyzeService.selectAndOrder();
        List<String> types=new ArrayList<>();//存储所有的分组类型
        List<String> totalNumber=new ArrayList<>();//存储所有的销售总额
        for (Map<String, Object> item : result) {
            //根据每一个map里的分组类型（列名），取出对应的分组类型（页面上的）,销售总额也是这样
            types.add(item.get("name").toString());//拿到当前map的分组类型,添加到集合中
            totalNumber.add(item.get("totalNumber").toString());//拿到当前map的销售总额，添加到集合中
        }
        model.addAttribute("types", JSON.toJSONString(types));//转换成json格式，共享给页面
        model.addAttribute("totalNumber", JSON.toJSONString(totalNumber));
        return "memberAnalyze";
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @RequestMapping("queryByDate")
    @ResponseBody
    public Object queryByDate(MemberAnalyzeObject qo){
        maps=memberAnalyzeService.queryByDate(qo);
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
        response.setHeader("Content-Disposition","attachment;filename=memberAnalyze.xls");
        //创建excel文件
        Workbook wb=new HSSFWorkbook();
        //创建工作簿
        Sheet sheet=wb.createSheet("day01");
        //设置标题:索引从0开始
        Row row=sheet.createRow(0);
        row.createCell(0).setCellValue("会员卡号");
        row.createCell(1).setCellValue("会员名称");
        row.createCell(2).setCellValue("消费笔数");
        row.createCell(3).setCellValue("消费金额");
        row.createCell(4).setCellValue("消费店铺");
        row.createCell(5).setCellValue("总店");
        //查出所有的产品分析
        for(int i=0;i<maps.size();i++){
            //拿到每一个map：一条数据
            Map<String,Object> map=maps.get(i);
            //创建行
            row=sheet.createRow(i+1);
            //设置单元格内容
            row.createCell(0).setCellValue((map.get("memberNum")).toString());
            row.createCell(1).setCellValue((String)map.get("name"));
            row.createCell(2).setCellValue((map.get("totalNumber")).toString());
            row.createCell(3).setCellValue((map.get("totalAmount")).toString());
            row.createCell(4).setCellValue("德客便利店");
            row.createCell(5).setCellValue("德客便利店");
        }
        //写入数据，输出到浏览器
        wb.write(response.getOutputStream());
    }
}
