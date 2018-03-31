package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.RefundItem;

import java.util.List;

public interface RefundItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RefundItem entity);

    List<RefundItem> selectAll();

    void deleteByRefundId(Long id);
}