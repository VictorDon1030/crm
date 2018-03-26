package cn.wolfcode.crm.domain;


import lombok.Getter;
import lombok.Setter;

//数据字典明细
@Getter@Setter
public class DictionaryItem extends BaseDomain {

    //明细名称
    private String name;
    //明细简介
    private String intro;
//数据字典Id;
    private Dictionary dictionary;

}