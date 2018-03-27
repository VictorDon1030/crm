package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PayItem;

import java.util.List;

public interface IPayItemService {
    void delete(Long id);

    void insert(PayItem entity);

    PayItem selectByPrimaryKey(Long id);

    List<PayItem> selectAll();

    void update(PayItem entity);
}
