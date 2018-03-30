package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;
import java.util.Map;

public interface IPayService {
    void delete(Long id);

    void insert(Pay entity);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    void update(Pay entity);

    /***
     * 高级查询
     */
    List<Map<String,Object>> query(QueryObject qo);

    /***
     * 按照日期降序查询，取出前5笔支出
     */
    List<Map<String,Object>> selectBeforeFiveByDate();
}
