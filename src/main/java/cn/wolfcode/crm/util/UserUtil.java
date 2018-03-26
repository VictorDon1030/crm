package cn.wolfcode.crm.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public class UserUtil {

    public static HttpServletRequest getRequest(){
        ServletRequestAttributes req = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return req.getRequest();
    }
}
