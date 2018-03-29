package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ProductTwo;
import cn.wolfcode.crm.mapper.ProductTwoMapper;
import cn.wolfcode.crm.service.IProductTwoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by China on 2018/3/26.
 */
@Service
public class ProductTwoServiceImpl implements IProductTwoService {
    @Autowired
    private ProductTwoMapper productTwoMapper;

    @Override
    public void saveOrUpdate(ProductTwo entity) {
        if (entity.getId() == null) {
            productTwoMapper.insert(entity);
        }else {
            productTwoMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {
        return productTwoMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductTwo get(Long id) {
        return productTwoMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductTwo> listAll() {
        return productTwoMapper.selectAll();
    }


}