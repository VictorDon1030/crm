package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.service.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Collection;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    RequestMappingHandlerMapping handlerMapping;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Permission entity) {
        return permissionMapper.insert(entity);
    }

    @Override
    public List<Permission> selectAll() {
        return permissionMapper.selectAll();
    }

    @Override
    public void reload() {
        List<String> resources = permissionMapper.selectAllResource();
        Collection<HandlerMethod> methods = handlerMapping.getHandlerMethods().values();
        for (HandlerMethod method : methods) {
            RequiresPermissions annotation = method.getMethodAnnotation(RequiresPermissions.class);
            if (annotation != null) {
                String[] value = annotation.value();
                if (!resources.contains(value[0])) {
                    Permission permission = new Permission();
                    permission.setName(value[1]);
                    permission.setResource(value[0]);
                    permissionMapper.insert(permission);
                }
            }
        }
    }

}
