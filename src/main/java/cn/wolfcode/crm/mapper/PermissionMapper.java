package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission entity);

    Permission selectByPrimaryKey(Long id);

    List<Permission> selectAll();

    int updateByPrimaryKey(Permission entity);

    List<String> selectAllResource();

    List<String> selectByEmployee(Long id);
}