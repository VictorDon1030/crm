package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.MinType;
import java.util.List;

public interface MinTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MinType entity);

    MinType selectByPrimaryKey(Long id);

    List<MinType> selectAll();

    int updateByPrimaryKey(MinType entity);
}