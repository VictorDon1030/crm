package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**支出明细*/
@Getter@Setter
public class PayItem extends BaseDomain{

    private String type;

    private Long amount;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private Long employee_id;

    private String remark;

    //支出人
    private Employee payUser;
    //维护与支出的关系:一对一，支出明细表需要支出表的数据
    private Pay pay;
}