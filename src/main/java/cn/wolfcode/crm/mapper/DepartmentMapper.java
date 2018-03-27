package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department entity);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department entity);


    void changeState(Long id);

    int queryCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);
}