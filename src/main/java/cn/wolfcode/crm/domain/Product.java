package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
//商品表
public class Product extends BaseDomain {
    //商品名称
    private String name;
    //商品品牌
    private String brand;
    //助词码
    private String auxiliaryWord;
    //商品货号
    private String goodsMark;
    //参考进价
    private BigDecimal purchasingPrice;
    //销售单价
    private BigDecimal unitpPrice;
    //会员售价
    private String memberPrice;
    //会员折扣
    private BigDecimal minDiscount;
    //最低售价
    private BigDecimal minPrice;
    //初始库存
    private String initialInventory;
    //商品规格
    private String specification;
    //商品单位
    private String unit;
    //商品积分
    private Long integral;
    //商品提成
    private BigDecimal commission;
    //过期时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")//后台传前台
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前台传后台
    private Date pastDueTime;
    //备注信息
    private String remark;
    //商品图片
    private String imagePath;
}

