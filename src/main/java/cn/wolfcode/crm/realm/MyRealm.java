package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.EmployeeMapper;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.mapper.RoleMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Employee employee = (Employee) principals.getPrimaryPrincipal();
        if (employee.getAdmin()) {
            info.addStringPermission("*:*");
            info.addRoles(roleMapper.selectAllRoleName());
        } else {
            info.addRoles(roleMapper.selectByEmployee(employee.getId()));
            info.addStringPermissions(permissionMapper.selectByEmployee(employee.getId()));
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = (String) token.getPrincipal();
        Employee employee = employeeMapper.selectByEmployeeName(principal);
        if (employee == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(employee,employee.getPassword(),this.getName());
    }
}
