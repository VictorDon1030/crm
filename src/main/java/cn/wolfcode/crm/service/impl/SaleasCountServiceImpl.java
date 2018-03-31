package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SaleasCount;
import cn.wolfcode.crm.mapper.SaleasCountMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISaleasCountService;
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
public class SaleasCountServiceImpl implements ISaleasCountService {

    @Autowired
    private SaleasCountMapper saleasCountMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return saleasCountMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SaleasCount entity) {
        return saleasCountMapper.insert(entity);
    }

    @Override
    public SaleasCount selectByPrimaryKey(Long id) {
        return saleasCountMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SaleasCount> selectAll() {
        return saleasCountMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SaleasCount entity) {
        return saleasCountMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = saleasCountMapper.queryCount(qo);
        List<SaleasCount> data = saleasCountMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
}
