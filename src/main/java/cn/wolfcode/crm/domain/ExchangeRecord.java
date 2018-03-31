package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员积分兑换礼品记录实体类
 */
@Getter
@Setter
public class ExchangeRecord extends BaseDomain {

    //兑换的数量
    private Integer number;
    //花费的积分
    private Integer costPoints;
    //操作人员
    private Employee optUser;
    //兑换的店铺
    private String consumeStore;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date exchangeDate;
    //维护与礼品的多对一关系
    private Gift gift;
    //维护与会员的多对多关系
    private List<Member> members = new ArrayList<>(10);

}