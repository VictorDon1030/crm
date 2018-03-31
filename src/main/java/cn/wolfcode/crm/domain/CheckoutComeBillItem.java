package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class CheckoutComeBillItem {
    private Long id;

    private BigDecimal unitpPrice;

    private BigDecimal number;

    private BigDecimal amount;

    private Product product;

    private Long billId;

    private Member member;

}