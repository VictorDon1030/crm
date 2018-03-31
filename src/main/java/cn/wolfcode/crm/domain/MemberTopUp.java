package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MemberTopUp extends BaseDomain {


    //充值金额
    private BigDecimal addbalance;
    //充值次数
    private BigDecimal addcount;
    //赠送金额
    private BigDecimal give;
    //充值的时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toptime;
    //备注
    private String intro;
    //会员对象
    private Member member;
    //商品
    private Long product_id;
    //优惠方式
    private String discount;

    //充值状态,0表示冲次,1表示充值
    private boolean state;
    //表示1充值.-1扣费,0退还
    private int addway;
    //操作员工
    private Employee onUser;

    //支付方式 ,使用明细对象
    private DictionaryItem payment;



}