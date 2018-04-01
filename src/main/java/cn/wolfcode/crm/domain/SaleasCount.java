package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class SaleasCount {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台传后台
    private Date vdate;
    //出售的数量
    private BigDecimal number;
    //
    private BigDecimal costPrice;

    private BigDecimal costAmount;

    private BigDecimal salePrice;

    private BigDecimal saleAmount;

    private  Product product;

    private Employee saleman;

    private Member member;

    private List<Product> products = new ArrayList<>();
}