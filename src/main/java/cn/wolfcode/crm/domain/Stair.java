package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Stair extends BaseDomain {
    //一级 商品
    private String name;

    //二级商品 一对多
    private Long secondary_id;

}