package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface GiftMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Gift entity);

    Gift selectByPrimaryKey(Long id);

    List<Gift> selectAll();

    int updateByPrimaryKey(Gift entity);

    int query4count(QueryObject qo);

    List<Gift> query4list(QueryObject qo);
}