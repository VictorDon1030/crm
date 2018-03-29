package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DepotMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Depot entity);

    Depot selectByPrimaryKey(Long id);

    List<Depot> selectAll();

    int updateByPrimaryKey(Depot entity);
    int queryCount(QueryObject qo);

    List<Depot> queryForList(QueryObject qo);

    void changeState(Long id);
}