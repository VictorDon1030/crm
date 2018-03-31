package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.InventoryItem;
import cn.wolfcode.crm.query.InventoryQueryObject;

import java.util.List;

public interface InventoryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(InventoryItem entity);

    InventoryItem selectByPrimaryKey(Long id);

    List<InventoryItem> selectAll();

    int updateByPrimaryKey(InventoryItem entity);

    int query4Count(InventoryQueryObject qo);

    List<InventoryItem> query4List(InventoryQueryObject qo);
}