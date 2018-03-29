package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IMemberTopUpService {

    int deleteByPrimaryKey(Long id);

    int insert(MemberTopUp entity);

    MemberTopUp selectByPrimaryKey(Long id);

    List<MemberTopUp> selectAll();

    int updateByPrimaryKey(MemberTopUp entity);

    PageResult query(QueryObject qo);


}
