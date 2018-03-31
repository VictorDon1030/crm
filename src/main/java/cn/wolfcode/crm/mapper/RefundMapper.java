package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.Refund;
import cn.wolfcode.crm.query.RefundQueryObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface RefundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Refund entity);

    Refund selectByPrimaryKey(Long id);

    List<Refund> selectAll();

    int updateByPrimaryKey(Refund entity);
    List<Refund> query4List(QueryObject qo);
    int query4Count(RefundQueryObject qo);

    void audit(Refund refund);



}