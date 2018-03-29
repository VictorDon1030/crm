package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.ExchangeRecordQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IExchangeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
