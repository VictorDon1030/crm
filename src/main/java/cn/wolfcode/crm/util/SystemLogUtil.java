package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.mapper.SystemLogMapper;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public class SystemLogUtil {

    @Autowired
    private SystemLogMapper systemLogMapper;

    public void writeLog(JoinPoint joinPoint){
        SystemLog systemLog = new SystemLog();

        systemLog.setOpTime(new Date());
        Object principal = SecurityUtils.getSubject().getPrincipal();
        systemLog.setOpUser((Employee) principal);

        String addr = UserUtil.getRequest().getRemoteAddr();
        systemLog.setOpIp(addr);

        Object[] args = joinPoint.getArgs();
        try {
            systemLog.setParams(JSON.toJSONString(args));
        } catch (Exception e){
            e.printStackTrace();
        }

        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        systemLog.setFunction(className + ":" +methodName);

        systemLogMapper.insert(systemLog);
    }
}
