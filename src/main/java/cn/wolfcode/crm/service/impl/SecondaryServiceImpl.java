package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Secondary;
import cn.wolfcode.crm.mapper.SecondaryMapper;
import cn.wolfcode.crm.service.ISecondaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by China on 2018/3/26.
 */
@Service
public class SecondaryServiceImpl implements ISecondaryService {
    @Autowired
    private SecondaryMapper secondaryMapper;

    @Override
    public void saveOrUpdate(Secondary entity) {
        if (entity.getId() == null) {
            secondaryMapper.insert(entity);
        }else {
            secondaryMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {
        return secondaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Secondary get(Long id) {
        return secondaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Secondary> listAll() {
        return secondaryMapper.selectAll();
    }


}