package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderBillItem extends BaseDomain {

    //成本价
    private BigDecimal costPrice;
    //商品数量
    private BigDecimal number;
    //总价
    private BigDecimal amount;
    //商品介绍
    private String remark;

    private Long billId;
    private Product product;


}