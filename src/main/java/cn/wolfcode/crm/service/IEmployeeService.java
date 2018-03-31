package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IEmployeeService {

    int deleteByPrimaryKey(Long id);

    int insert(Employee entity, Long[] ids);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee entity, Long[] ids);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    void deleteAll();
}
