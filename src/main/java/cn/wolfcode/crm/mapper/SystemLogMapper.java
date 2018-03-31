package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog entity);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog entity);

    int queryCount(QueryObject qo);

    List<SystemLog> queryForList(QueryObject qo);

    void deleteAll();
}