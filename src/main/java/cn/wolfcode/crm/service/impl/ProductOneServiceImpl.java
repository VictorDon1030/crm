package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ProductOne;
import cn.wolfcode.crm.mapper.ProductOneMapper;
import cn.wolfcode.crm.service.IProductOneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by China on 2018/3/26.
 */
@Service
public class ProductOneServiceImpl implements IProductOneService {
    @Autowired
    private ProductOneMapper productOneMapper;

    @Override
    public void saveOrUpdate(ProductOne entity) {
        if (entity.getId() == null) {
            productOneMapper.insert(entity);
        }else {
            productOneMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {
        return productOneMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ProductOne get(Long id) {
        return productOneMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProductOne> listAll() {
        return productOneMapper.selectAll();
    }


}