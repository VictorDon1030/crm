package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface PayItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PayItem entity);

    PayItem selectByPrimaryKey(Long id);

    List<PayItem> selectAll();

    int updateByPrimaryKey(PayItem entity);

    /***
     * 高级查询
     */
    int queryCount(QueryObject qo);

    List<PayItem> queryForList(QueryObject qo);
}