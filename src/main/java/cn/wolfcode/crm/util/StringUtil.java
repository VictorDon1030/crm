package cn.wolfcode.crm.util;

import org.springframework.util.StringUtils;

/**
 * 空字符串转换为null工具类,可减少SQL中的不为空的判断
 * @author 应举
 *
 */
public abstract class StringUtil {

    /**
     * 将空字符串转换成null的方法
     * @param string 调用者传入的数据
     * @return 如果参数为空则返回null,否则将调用者传入的数据返回
     */
    public static String empty2null(String string){
        return StringUtils.isEmpty(string) ? null : string;
    }
}
