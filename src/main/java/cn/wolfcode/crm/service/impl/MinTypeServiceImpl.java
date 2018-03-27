package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.MinType;
import cn.wolfcode.crm.mapper.MinTypeMapper;
import cn.wolfcode.crm.service.IMinTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinTypeServiceImpl implements IMinTypeService {
    @Autowired
    private MinTypeMapper minTypeMapper;

    @Override
    public void delete(Long id) {
        minTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(MinType entity) {
        minTypeMapper.insert(entity);
    }

    @Override
    public MinType selectByPrimaryKey(Long id) {
        return minTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MinType> selectAll() {
        return minTypeMapper.selectAll();
    }

    @Override
    public void update(MinType entity) {
        minTypeMapper.updateByPrimaryKey(entity);
    }
}
