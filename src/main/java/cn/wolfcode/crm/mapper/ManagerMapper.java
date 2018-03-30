package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Manager;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ManagerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Manager entity);

    Manager selectByPrimaryKey(Long id);

    List<Manager> selectAll();

    int updateByPrimaryKey(Manager entity);
    int queryCount(QueryObject qo);

    List<Manager> queryForList(QueryObject qo);
}