package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.MaxType;
import java.util.List;

public interface MaxTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MaxType entity);

    MaxType selectByPrimaryKey(Long id);

    List<MaxType> selectAll();

    int updateByPrimaryKey(MaxType entity);
}