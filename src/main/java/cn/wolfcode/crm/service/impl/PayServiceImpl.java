package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.mapper.PayMapper;
import cn.wolfcode.crm.service.IPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PayServiceImpl implements IPayService {
    @Autowired
    private PayMapper payMapper;

    @Override
    public void delete(Long id) {
        payMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(Pay entity) {
        payMapper.insert(entity);
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
