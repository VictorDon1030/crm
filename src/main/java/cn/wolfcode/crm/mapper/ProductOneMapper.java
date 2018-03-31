package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ProductOne;
import java.util.List;

public interface ProductOneMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductOne entity);

    ProductOne selectByPrimaryKey(Long id);

    List<ProductOne> selectAll();

    int updateByPrimaryKey(ProductOne entity);
}