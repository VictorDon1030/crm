package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.SubBranch;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface SubBranchMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SubBranch entity);

    SubBranch selectByPrimaryKey(Long id);

    List<SubBranch> selectAll();

    int updateByPrimaryKey(SubBranch entity);

    int queryCount(QueryObject qo);

    List<SubBranch> queryForList(QueryObject qo);

    void changeState(Long id);
    void changeMallState(Long id);
}