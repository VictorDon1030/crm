package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.JoinApply;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IJoinApplyService {

    int deleteByPrimaryKey(Long id);

    int insert(JoinApply entity);

    JoinApply selectByPrimaryKey(Long id);

    List<JoinApply> selectAll();

    int updateByPrimaryKey(JoinApply entity);

    PageResult query(QueryObject qo);
}
