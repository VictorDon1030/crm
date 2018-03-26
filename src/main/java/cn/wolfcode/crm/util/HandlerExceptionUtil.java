package cn.wolfcode.crm.util;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Controller
public class HandlerExceptionUtil {


    @ExceptionHandler(UnauthorizedException.class)
    public void handlerException(HttpServletResponse response,
                                 HandlerMethod handlerMethod) throws Exception{
        ResponseBody annotation = handlerMethod.getMethodAnnotation(ResponseBody.class);
        if (annotation != null) {
            JsonResult result = new JsonResult();
            result.mark("没有权限");
            response.getWriter().print(JSON.toJSONString(result));
        } else {
            response.sendRedirect("/nopermission.jsp");
        }
    }
}
