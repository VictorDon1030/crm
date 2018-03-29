package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

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
    //表示充值.扣费,退还
    private DictionaryItem addway;

}