package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ProductOne;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
public interface IProductOneService {

    void saveOrUpdate(ProductOne entity);

    int delete(Long id);

    ProductOne get(Long id);

    List<ProductOne> listAll();

}
