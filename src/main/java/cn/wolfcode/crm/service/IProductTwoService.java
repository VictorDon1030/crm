package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ProductTwo;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
public interface IProductTwoService {

    void saveOrUpdate(ProductTwo entity);

    int delete(Long id);

    ProductTwo get(Long id);

    List<ProductTwo> listAll();

}
