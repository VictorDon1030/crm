package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role entity);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role entity);

    int queryCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId,@Param("permissionId") Long permissionId);

    void deleteRealtion(Long id);

    List<Long> selectRoleIdByEmpId(Long id);

    List<String> selectByEmployee(Long id);
    List<String> selectAllRoleName();
}