package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductStockMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductStock entity);

    ProductStock selectByPrimaryKey(Long id);

    List<ProductStock> selectAll();

    int updateByPrimaryKey(ProductStock entity);

    ProductStock selectByProductIdDepotId(@Param("productId") Long productId, @Param("depotId") Long depotId);

    List<ProductStock> query4List(ProductStockQueryObject qo);
    Integer query4Count(ProductStockQueryObject qo);

}