package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.mapper.PayItemMapper;
import cn.wolfcode.crm.mapper.PayMapper;
import cn.wolfcode.crm.service.IPayService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PayServiceImpl implements IPayService {
    @Autowired
    private PayMapper payMapper;
    @Autowired
    private PayItemMapper payItemMapper;

    @Override
    public void delete(Long id) {
        payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Pay entity) {
        //插入支出
        payMapper.insert(entity);
        //插入支出明细
        PayItem payItem=new PayItem();
        payItem.setId(entity.getId());
        payItem.setType(entity.getType());
        payItem.setAmount(entity.getAmount());
        payItem.setDate(entity.getDate());
        Employee e=(Employee) SecurityUtils.getSubject().getPrincipal();
        payItem.setPayUser(e);
        payItem.setRemark(entity.getRemark());
        payItem.setPay(entity);
        payItemMapper.insert(payItem);
    }

    @Override
    public Pay selectByPrimaryKey(Long id) {
        return payMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Pay> selectAll() {
        return payMapper.selectAll();
    }

    @Override
    public void update(Pay entity) {
        payMapper.updateByPrimaryKey(entity);
    }
}
