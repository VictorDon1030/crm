package cn.wolfcode.crm.domain;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 数据字典类
 * 可以用于存储会员卡等级之类的分级机制
 */
@Getter
@Setter
public class Dictionary extends BaseDomain {


    //字段目录编码
    private String sn;
    //字典名称
    private String name;
    //字典简介
    private String intro;
    //字典明细,一对多
    private List<DictionaryItem> items;
}