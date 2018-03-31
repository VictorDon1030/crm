package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.JoinApply;
import cn.wolfcode.crm.mapper.JoinApplyMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IJoinApplyService;
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
public class JoinApplyServiceImpl implements IJoinApplyService {

    @Autowired
    private JoinApplyMapper joinApplyMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return joinApplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(JoinApply entity) {
        return joinApplyMapper.insert(entity);
    }

    @Override
    public JoinApply selectByPrimaryKey(Long id) {
        return joinApplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<JoinApply> selectAll() {
        return joinApplyMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(JoinApply entity) {
        return joinApplyMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = joinApplyMapper.queryCount(qo);
        List<JoinApply> data = joinApplyMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
}
