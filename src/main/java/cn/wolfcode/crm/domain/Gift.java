package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Gift extends BaseDomain {

    private String name;
    //礼品编码
    private String sn;
    //礼品单位
    private String unit;
    //兑换礼品所需积分
    private Integer points;
    //总数量
    private Integer totalQuantity;
    //剩余数量
    private Integer inventory;

}