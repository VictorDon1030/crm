package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Stair;
import java.util.List;

public interface StairMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Stair entity);

    Stair selectByPrimaryKey(Long id);

    List<Stair> selectAll();

    int updateByPrimaryKey(Stair entity);
}