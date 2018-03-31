package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface ISupplierService {
    int deleteByPrimaryKey(Long var1);

    int save(Supplier var1);

    Supplier selectByPrimaryKey(Long var1);

    List<Supplier> selectAll();

    PageResult query(QueryObject var1);
}



