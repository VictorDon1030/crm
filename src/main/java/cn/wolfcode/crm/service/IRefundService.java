package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.Refund;
import cn.wolfcode.crm.query.RefundQueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IRefundService {
    void saveOrUpdate(Refund entity);
    void delete(Long id);
    Refund get(Long id);
    List<Refund> list();
    PageResult query(RefundQueryObject qo);

    void audit(Long id);
}
