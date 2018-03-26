package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IRoleService {

    int deleteByPrimaryKey(Long id);

    int insert(Role entity,Long[] ids);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity,Long[] ids);

    PageResult query(QueryObject qo);

    List<Long> selectRoleIdByEmpId(Long id);
}
