package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.MaxType;
import cn.wolfcode.crm.mapper.MaxTypeMapper;
import cn.wolfcode.crm.service.IMaxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaxTypeServiceImpl implements IMaxTypeService {
    @Autowired
    private MaxTypeMapper maxTypeMapper;

    @Override
    public void delete(Long id) {
        maxTypeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(MaxType entity) {
        maxTypeMapper.insert(entity);
    }

    @Override
    public MaxType selectByPrimaryKey(Long id) {
        return maxTypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MaxType> selectAll() {
        return maxTypeMapper.selectAll();
    }

    @Override
    public void update(MaxType entity) {
        maxTypeMapper.updateByPrimaryKey(entity);
    }
}
