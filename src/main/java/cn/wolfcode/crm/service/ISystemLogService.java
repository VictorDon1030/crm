package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.query.MenuQuery;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface ISystemLogService {

    int insert(SystemLog entity);

    List<SystemLog> selectAll();

    PageResult query(QueryObject qo);

}
