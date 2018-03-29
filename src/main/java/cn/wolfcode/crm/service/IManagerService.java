package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Manager;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IManagerService {

    int deleteByPrimaryKey(Long id);

    int insert(Manager entity);

    Manager selectByPrimaryKey(Long id);

    List<Manager> selectAll();

    int updateByPrimaryKey(Manager entity);

    PageResult query(QueryObject qo);
}
