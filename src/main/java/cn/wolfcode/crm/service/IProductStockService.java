package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IProductStockService {
    PageResult query(ProductStockQueryObject qo);
    List<ProductStock> selectAll();
}
