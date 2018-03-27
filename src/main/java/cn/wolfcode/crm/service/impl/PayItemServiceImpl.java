package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.mapper.PayItemMapper;
import cn.wolfcode.crm.service.IPayItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PayItemServiceImpl implements IPayItemService {
    @Autowired
    private PayItemMapper payItemMapper;
    
    @Override
    public void delete(Long id) {
        payItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(PayItem entity) {
        payItemMapper.insert(entity);
    }

    @Override
    public PayItem selectByPrimaryKey(Long id) {
        return payItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PayItem> selectAll() {
        return payItemMapper.selectAll();
    }

    @Override
    public void update(PayItem entity) {
        payItemMapper.updateByPrimaryKey(entity);
    }
}
