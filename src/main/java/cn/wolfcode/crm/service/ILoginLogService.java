package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.LoginLog;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface ILoginLogService {

    int insert(LoginLog entity);

    List<LoginLog> selectAll();

    PageResult query(QueryObject qo);
}
