package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
public interface IProductService {

    void saveOrUpdate(Product entity);

    void save(Product entity);

    int delete(Long id);

    Product get(Long id);

    List<Product> listAll();

    PageResult query(QueryObject qo);

    List<Product> selectByUnitId(Long id);

    void deleteAll();
}
