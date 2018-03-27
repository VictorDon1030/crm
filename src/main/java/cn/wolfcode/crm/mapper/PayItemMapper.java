package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.PayItem;
import java.util.List;

public interface PayItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayItem entity);

    PayItem selectByPrimaryKey(Long id);

    List<PayItem> selectAll();

    int updateByPrimaryKey(PayItem entity);
}