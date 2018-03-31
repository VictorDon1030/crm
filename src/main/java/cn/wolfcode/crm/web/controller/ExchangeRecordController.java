package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.query.ExchangeRecordQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IExchangeRecordService;
import cn.wolfcode.crm.util.JsonResult;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("exchangeRecord")
public class ExchangeRecordController {
    @Autowired
    private IExchangeRecordService exchangeRecordService;

    @RequestMapping("list")
    @ResponseBody
    public Object query(ExchangeRecordQueryObject qo) throws Exception {
        return exchangeRecordService.query(qo);
    }

    @RequestMapping("save")
    @ResponseBody
    public Object save(ExchangeRecord entity) throws Exception {
        JsonResult jsonResult = new JsonResult();
        try {

            exchangeRecordService.save(entity);
        } catch (Exception e){
            jsonResult.mark(e.getMessage());
        }
        return jsonResult;
    }
    @RequestMapping("exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response) throws Exception {

            exchangeRecordService.exportExcel(response);
    }
}
