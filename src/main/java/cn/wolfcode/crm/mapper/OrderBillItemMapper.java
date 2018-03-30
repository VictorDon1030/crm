package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.OrderBillItem;

import java.util.List;

public interface OrderBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderBillItem entity);

    List<OrderBillItem> selectAll();

    void deleteByBillId(Long id);
}