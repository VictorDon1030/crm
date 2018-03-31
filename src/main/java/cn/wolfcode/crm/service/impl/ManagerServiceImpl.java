package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Manager;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.ManagerMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IManagerService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return managerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Manager entity) {
        Employee employee = employeeMapper.selectByPrimaryKey(Long.parseLong(entity.getName()));
        List<Role> roles = employee.getRoles();
        if (roles.isEmpty()) {
            throw new RuntimeException("该用户没有职位,不能当管理");
        }
        entity.setRole(roles.get(0));
        entity.setName(employee.getUsername());
        entity.setHireDate(new Date());
        return managerMapper.insert(entity);
    }

    @Override
    public Manager selectByPrimaryKey(Long id) {
        return managerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Manager> selectAll() {
        return managerMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Manager entity) {
        return managerMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = managerMapper.queryCount(qo);
        List<Manager> data = managerMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
}
