package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台传后台
    private Date pastDueTime;

    private String imagePath;

}