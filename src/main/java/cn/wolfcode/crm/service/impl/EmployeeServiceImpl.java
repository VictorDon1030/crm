package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        employeeMapper.deleteRelation(id);
        return employeeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Employee entity, Long[] ids) {
        employeeMapper.insert(entity);
        if (ids != null) {
            for (Long id : ids) {
                employeeMapper.insertRelation(entity.getId(),id);
            }
        }
        return 1;
    }

    @Override
    public Employee selectByPrimaryKey(Long id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Employee> selectAll() {
        return employeeMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Employee entity, Long[] ids) {
        employeeMapper.deleteRelation(entity.getId());
        if (ids != null) {
            for (Long id : ids) {
                employeeMapper.insertRelation(entity.getId(),id);
            }
        }
        return employeeMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = employeeMapper.queryCount(qo);
        List<Employee> data = employeeMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void changeState(Long id) {
        employeeMapper.changeState(id);
    }

    @Override
    public void deleteAll() {
        employeeMapper.deleteAll();
    }
}
