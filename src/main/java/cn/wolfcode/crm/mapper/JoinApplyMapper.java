package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.JoinApply;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface JoinApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(JoinApply entity);

    JoinApply selectByPrimaryKey(Long id);

    List<JoinApply> selectAll();

    int updateByPrimaryKey(JoinApply entity);

    int queryCount(QueryObject qo);

    List<JoinApply> queryForList(QueryObject qo);
}