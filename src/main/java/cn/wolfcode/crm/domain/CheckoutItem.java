package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
public class CheckoutItem {
    private Long id;

    private BigDecimal unitpPrice;

    private BigDecimal number;

    private BigDecimal amount;

    private Product product_id;

    private Boolean status;

    private Long bill_id;

    private Long member_id;

}