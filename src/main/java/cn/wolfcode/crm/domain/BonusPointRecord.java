package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class BonusPointRecord extends BaseDomain {

    //操作人
    private Employee optUser;
    //操作类型
    private int type;
    //改变的数量
    private BigDecimal amount;
    //操作日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date optDate;
    //备注
    private String remark;

}