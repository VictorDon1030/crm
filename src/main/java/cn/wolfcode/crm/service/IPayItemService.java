package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IPayItemService {
    void delete(Long id);

    void insert(PayItem entity);

    PayItem selectByPrimaryKey(Long id);

    List<PayItem> selectAll();

    void update(PayItem entity);

    /***
     * 高级查询
     */
    PageResult query(QueryObject qo);
}
