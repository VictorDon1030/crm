package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * ProductStock 商品库存
 * @author Administrator
 * @date 2018/3/29 
 */
@Getter
@Setter
public class ProductStock extends BaseDomain{
    //均价
    private BigDecimal price;
    //库存数量
    private BigDecimal storeNumber;
    //金额
    private BigDecimal amount;

    private Product product;

    private Depot depot;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    //入库时间
    private Date auditTime;
}