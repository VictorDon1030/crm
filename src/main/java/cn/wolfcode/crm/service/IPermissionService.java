package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IPermissionService {

    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    List<Permission> selectAll();

    void reload();
}
