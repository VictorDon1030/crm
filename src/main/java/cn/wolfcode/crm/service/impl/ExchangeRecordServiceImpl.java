package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.mapper.ExchangeRecordMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IExchangeRecordService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExchangeRecordServiceImpl implements IExchangeRecordService {
    @Autowired
    private ExchangeRecordMapper exchangeRecordMapper;

    @Override
    public PageResult query(QueryObject qo) {
        int total = exchangeRecordMapper.query4count(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<ExchangeRecord> rows = exchangeRecordMapper.query4list(qo);
        return new PageResult(total,rows);
    }
}
