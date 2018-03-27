package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.mapper.ProductMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IProductService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by China on 2018/3/26.
 */
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void saveOrUpdate(Product entity) {
        if (entity.getId() == null) {
            productMapper.insert(entity);
        }else {
            productMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {
        return productMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Product get(Long id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> listAll() {
        return productMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = productMapper.query4Cont(qo);
        List<Product> data = productMapper.query4List(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

}