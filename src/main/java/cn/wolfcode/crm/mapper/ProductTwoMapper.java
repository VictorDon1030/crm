package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ProductTwo;
import java.util.List;

public interface ProductTwoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductTwo entity);

    ProductTwo selectByPrimaryKey(Long id);

    List<ProductTwo> selectAll();

    int updateByPrimaryKey(ProductTwo entity);
}