package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class SaleasCount {
    private Long id;

    private Date vdate;

    private BigDecimal number;

    private BigDecimal costPrice;

    private BigDecimal costAmount;

    private BigDecimal salePrice;

    private BigDecimal saleAmount;

    private Product product;

    private Employee saleman;

    private Member member;

}