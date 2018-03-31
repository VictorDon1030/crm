package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinApply extends BaseDomain {

    //店铺名字
    private String shopName;

    //电话
    private String tel;

    //邮箱
    private String email;

    //申请类型 : 个体/企业
    private Boolean accountType;

    //银行名称
    private String bankName;

    //申请人名称
    private String accountName;
}