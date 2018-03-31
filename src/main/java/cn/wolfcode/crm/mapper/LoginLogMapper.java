package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.LoginLog;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface LoginLogMapper {

    int insert(LoginLog entity);

    List<LoginLog> selectAll();

    int queryCount(QueryObject qo);

    List<LoginLog> queryForList(QueryObject qo);
}