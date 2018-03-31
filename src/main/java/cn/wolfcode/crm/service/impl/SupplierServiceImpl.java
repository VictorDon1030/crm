package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Supplier;
import cn.wolfcode.crm.mapper.SupplierMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISupplierService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/3/27 23:25
 */
@Service
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;

    public int deleteByPrimaryKey(Long id) {
        return this.supplierMapper.deleteByPrimaryKey(id);
    }

    public int save(Supplier entity) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (entity.getId() == null) {
            entity.setVdate(new Date());
            entity.setEmployee(employee);
            return this.supplierMapper.insert(entity);
        } else {
            entity.setEmployee(employee);
            return this.supplierMapper.updateByPrimaryKey(entity);
        }
    }

    public Supplier selectByPrimaryKey(Long id) {
        return this.supplierMapper.selectByPrimaryKey(id);
    }

    public List<Supplier> selectAll() {
        return this.supplierMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        int total = this.supplierMapper.queryCount(qo);
        List<Supplier> data = this.supplierMapper.queryForList(qo);
        return total == 0 ? new PageResult(total, Collections.EMPTY_LIST) : new PageResult(total, data);
    }
}
