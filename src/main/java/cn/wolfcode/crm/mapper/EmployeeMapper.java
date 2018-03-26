package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee entity);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity);

    int queryCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    void changeState(Long id);

    void insertRelation(@Param("empId") Long empId,@Param("roleId") Long roleId);

    void deleteRelation(Long id);

    Employee selectByEmployeeName(String name);
}