package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.OrderBill;
import cn.wolfcode.crm.query.OrderBillQueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IOrderBillService {
    void saveOrUpdate(OrderBill entity);
    void delete(Long id);
    OrderBill get(Long id);
    List<OrderBill> list();
    PageResult query(OrderBillQueryObject qo);

    void audit(Long id);

    List<OrderBill> selectAll();
}
