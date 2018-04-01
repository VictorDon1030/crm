package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.SaleasCount;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SaleasCountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleasCount entity);

    SaleasCount selectByPrimaryKey(Long id);

    List<SaleasCount> selectAll();

    int updateByPrimaryKey(SaleasCount entity);

    int queryCount(QueryObject qo);

    List<SaleasCount> queryForList(QueryObject qo);
}