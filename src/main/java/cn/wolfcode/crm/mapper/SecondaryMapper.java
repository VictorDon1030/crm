package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Secondary;
import java.util.List;

public interface SecondaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Secondary entity);

    Secondary selectByPrimaryKey(Long id);

    List<Secondary> selectAll();

    int updateByPrimaryKey(Secondary entity);

    List<Secondary> selectByStairId(Long id);
}