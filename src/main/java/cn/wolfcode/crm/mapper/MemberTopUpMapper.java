package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface MemberTopUpMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MemberTopUp entity);

    MemberTopUp selectByPrimaryKey(Long id);

    List<MemberTopUp> selectAll();

    int updateByPrimaryKey(MemberTopUp entity);

    int queryCount(QueryObject qo);

    List<MemberTopUp> queryForList(QueryObject qo);
}