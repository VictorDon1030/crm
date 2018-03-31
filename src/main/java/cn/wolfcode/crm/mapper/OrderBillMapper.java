package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.OrderBill;
import cn.wolfcode.crm.query.OrderBillQueryObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBill entity);

    OrderBill selectByPrimaryKey(Long id);

    List<OrderBill> selectAll();

    int updateByPrimaryKey(OrderBill entity);
    List<OrderBill> query4List(QueryObject qo);
    int query4Count(OrderBillQueryObject qo);

    void audit(OrderBill orderBill);



}