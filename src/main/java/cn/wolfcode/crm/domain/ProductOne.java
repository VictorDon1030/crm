package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProductOne extends BaseDomain {

    private String name;

    private String brand;

    private String goodsMark;

    private Long unitpPrice;

    private Long minPrice;

    private String initialInventory;

    private String unit;

    private Long commission;

    private String remark;

    private String auxiliaryWord;

    private Long purchasingPrice;

    private Long memberPrice;

    private Long minDiscount;

    private String specification;

    private Long integral;

    private Date pastDueTime;

    private String imagePath;

}