package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
//销售单
public class CheckoutComeBill {
    private Long id;
    //销售单编号
    private String sn;
    //销售时间
    private Date vdate;
    //是否是挂单状态
    private Integer status;
    //总金额
    private BigDecimal totalAmount;
    //数量
    private BigDecimal totalNumber;
    //销售人
    private Employee salesman;

    private Long depotId;
    //会员
    private Member member;
    //一个订单对多条明细
    List<CheckoutComeBillItem> items = new ArrayList<>();
}