package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
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
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return departmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Department entity) {
        return departmentMapper.insert(entity);
    }

    @Override
    public Department selectByPrimaryKey(Long id) {
        return departmentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Department> selectAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Department entity) {
        return departmentMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = departmentMapper.queryCount(qo);
        List<Department> data = departmentMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void changeState(Long id) {
        departmentMapper.changeState(id);
    }

}
