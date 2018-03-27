package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Product;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product entity);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product entity);

    Integer query4Cont(QueryObject qo);

    List<Product> query4List(QueryObject qo);

}