package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CheckoutItem;
import java.util.List;

public interface CheckoutItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckoutItem entity);

    CheckoutItem selectByPrimaryKey(Long id);

    List<CheckoutItem> selectAll();

    int updateByPrimaryKey(CheckoutItem entity);

}