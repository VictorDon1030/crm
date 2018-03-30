package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Member extends BaseDomain {
    //会员编号
    private Integer memberNum;
    //会员名称
    private String name;
    //会员电话
    private String phone;
    //会员密码
    private String password;
    //会员等级,使用明细对象
    private DictionaryItem grade;
    //支付方式 ,使用明细对象
    private DictionaryItem payment;
    //积分
    private BigDecimal points;
    //余额
    private BigDecimal balance;
    //有效期
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date usefullife;
    //操作会员
    private Employee onUser;
    //会员状态
    private boolean state = true;
    //会员生日
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GTM+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    //维护与会员积分变化的关系
    private  List<BonusPointRecord> bonusPointRecord = new ArrayList<>(5);
    //会员已经消费的积分
    private Integer consumePoints;


}