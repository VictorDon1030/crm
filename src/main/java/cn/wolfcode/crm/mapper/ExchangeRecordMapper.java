package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ExchangeRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ExchangeRecord entity);

    ExchangeRecord selectByPrimaryKey(Long id);

    List<ExchangeRecord> selectAll();

    int updateByPrimaryKey(ExchangeRecord entity);

    int query4count(QueryObject qo);

    List<ExchangeRecord> query4list(QueryObject qo);
}