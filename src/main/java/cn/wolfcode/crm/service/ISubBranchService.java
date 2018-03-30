package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SubBranch;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface ISubBranchService {

    int deleteByPrimaryKey(Long id);

    int insert(SubBranch entity);

    SubBranch selectByPrimaryKey(Long id);

    List<SubBranch> selectAll();

    int updateByPrimaryKey(SubBranch entity);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    void changeMallState(Long id);
}
