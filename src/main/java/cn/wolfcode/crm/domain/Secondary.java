package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Secondary extends BaseDomain{
    //二级商品
    private String name;
    //一级商品 多对一
    private List<Product> product = new ArrayList<>();
    //多对一
    private List<Stair> stair = new ArrayList<>();

}