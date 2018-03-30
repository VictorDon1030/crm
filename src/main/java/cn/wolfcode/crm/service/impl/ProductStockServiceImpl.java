package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.mapper.ProductStockMapper;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.service.IProductStockService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;

    public PageResult query(ProductStockQueryObject qo) {
        int total = productStockMapper.query4Count(qo);
        List<?> data = productStockMapper.query4List(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public List<ProductStock> selectAll() {
        return productStockMapper.selectAll();
    }

    @Override
    public PageResult selectProductStockByDepotId(ProductStockQueryObject qo) {
        int total = productStockMapper.selectProductStockByDepotId4Count(qo);
        List<?> data = productStockMapper.selectProductStockByDepotId(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

}
