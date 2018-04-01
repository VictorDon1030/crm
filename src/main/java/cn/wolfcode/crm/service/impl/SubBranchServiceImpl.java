package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SubBranch;
import cn.wolfcode.crm.mapper.SubBranchMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISubBranchService;
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
public class SubBranchServiceImpl implements ISubBranchService {

    @Autowired
    private SubBranchMapper subBranchMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return subBranchMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(SubBranch entity) {
        return subBranchMapper.insert(entity);
    }

    @Override
    public SubBranch selectByPrimaryKey(Long id) {
        return subBranchMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SubBranch> selectAll() {
        return subBranchMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(SubBranch entity) {
        return subBranchMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = subBranchMapper.queryCount(qo);
        List<SubBranch> data = subBranchMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void changeState(Long id) {
        subBranchMapper.changeState(id);
    }

    @Override
    public void changeMallState(Long id) {
        subBranchMapper.changeMallState(id);
    }

}
