package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Refund extends BaseDomain {
    public static final int AUDIT_PASS=1;
    public static final int AUDIT_UNTREATED=0;
    private String sn;//退货订单编码

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date vdate;//业务时间

    private int status=AUDIT_UNTREATED;//审核状态

    private BigDecimal totalAmount;//总价钱

    private BigDecimal totalNumber;//总数量

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date auditTime;//审核时间

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date inputTime;//录入时间

    //录入人
    private Employee inputUser;
    //供应商
    private Supplier supplier;
    //仓库
    private Depot depot;
    //审核人
    private Employee auditor;
    //明细
    private List<RefundItem> items= new ArrayList<>();

}