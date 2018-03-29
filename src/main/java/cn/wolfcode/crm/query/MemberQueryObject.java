package cn.wolfcode.crm.query;

import cn.wolfcode.crm.domain.BaseDomain;
import cn.wolfcode.crm.util.MyStringUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class MemberQueryObject extends QueryObject {
//模糊查询
    private String keyword;
    //开始时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    //生日开始时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdayBeginDate;
    //生日结束时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdayEndDate;
//查询会员等级
    private Long gradeId = -1L;


public String getKeyword(){

    return MyStringUtil.emtpty2String(keyword);
}

}
