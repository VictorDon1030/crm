package cn.wolfcode.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by China on 2018/3/9.
 */
@Getter
@Setter
public class ProductQueryObject extends QueryObject{
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//后台传前台
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    //接口不用改 直接使用父类接收
    public String getKeyword(){
        return empty2Null(keyword);
    }
}
