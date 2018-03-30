package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.ExchangeRecordMapper;
import cn.wolfcode.crm.mapper.GiftMapper;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IExchangeRecordService;
import cn.wolfcode.crm.util.PageResult;
import com.sun.deploy.net.HttpResponse;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeRecordServiceImpl implements IExchangeRecordService {
    @Autowired
    private ExchangeRecordMapper exchangeRecordMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageResult query(QueryObject qo) {
        int total = exchangeRecordMapper.query4count(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<ExchangeRecord> rows = exchangeRecordMapper.query4list(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void save(ExchangeRecord entity) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        entity.setConsumeStore("德客便利店");
        entity.setExchangeDate(new Date());
        entity.setOptUser(employee);
        List<Member> members = entity.getMembers();
        exchangeRecordMapper.insert(entity);
        if (members != null) {
            for (Member member : members) {
                //维护中间表
                exchangeRecordMapper.insertRelation(member.getId(), entity.getId());
                Integer ret = entity.getCostPoints();
                try {
                    memberMapper.updateConsumePoints(member.getId(), ret);
                } catch (Exception e){
                    e.printStackTrace();
                }
                memberMapper.changePoint(member.getId(), BigDecimal.ZERO.subtract(new BigDecimal(entity.getCostPoints())));
            }
        }
        //让会员的积分减少
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws Exception {
        //设置文件下载响应头
        response.setHeader("content-disposition", "attachment;filename=exchangeRecord.xls");
        //创建Excel文档
        HSSFWorkbook wb = new HSSFWorkbook();
        //创建文件工作簿
        HSSFSheet sheet = wb.createSheet("record");
        //设置表格标题
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("会员卡号");
        row.createCell(1).setCellValue("会员名称");
        row.createCell(2).setCellValue("礼品名称");
        row.createCell(3).setCellValue("兑换数量");
        row.createCell(4).setCellValue("消费积分");
        row.createCell(5).setCellValue("消费店铺");
        row.createCell(6).setCellValue("操作人员");
        row.createCell(7).setCellValue("兑换日期");

        //找到要导出的数据
        List<ExchangeRecord> exchangeRecords = exchangeRecordMapper.selectAll();
        if (exchangeRecords != null) {
            for (int i = 0; i < exchangeRecords.size(); i++) {
                ExchangeRecord exchangeRecord = exchangeRecords.get(i);
                row = sheet.createRow(i + 1);
                List<Member> members = exchangeRecord.getMembers();
                if (members != null) {
                    for (Member member : members) {
                        if (member.getMemberNum() != null) {
                            row.createCell(0).setCellValue(member.getMemberNum());
                        }
                        if (member.getName() != null) {
                            row.createCell(1).setCellValue(member.getName());
                        }
                    }

                }
                Gift gift = exchangeRecord.getGift();
                if (gift != null) {
                    if (gift.getName() != null) {
                        row.createCell(2).setCellValue(gift.getName());
                    }

                }
                row.createCell(3).setCellValue(exchangeRecord.getNumber());
                row.createCell(4).setCellValue(exchangeRecord.getCostPoints());
                row.createCell(5).setCellValue(exchangeRecord.getConsumeStore());
                row.createCell(6).setCellValue(exchangeRecord.getOptUser().getUsername());
                row.createCell(7).setCellValue(exchangeRecord.getExchangeDate());
            }
            //写出到浏览器
            wb.write(response.getOutputStream());

        }

    }
}
