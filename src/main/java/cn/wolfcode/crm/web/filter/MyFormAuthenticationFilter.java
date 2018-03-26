package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.util.JsonResult;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public class MyFormAuthenticationFilter extends FormAuthenticationFilter {

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        response.getWriter().print(JSON.toJSONString(new JsonResult()));
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        String msg = "未知异常";
        if (e instanceof UnknownAccountException){
            msg = "用户名不存在";
        } else if (e instanceof IncorrectCredentialsException){
            msg = "密码错误";
        }
        JsonResult result = new JsonResult();
        try {
            result.mark(msg);
            response.getWriter().print(JSON.toJSONString(result));
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isLoginRequest(request,response)) {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
