package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.domain.PayItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface PayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pay entity);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    int updateByPrimaryKey(Pay entity);

    /***
     * 高级查询
     */
    List<Map<String,Object>> query(QueryObject qo);

    /***
     * 按照日期降序查询，取出前5笔支出
     */
    List<Map<String,Object>> selectBeforeFiveByDate();
}