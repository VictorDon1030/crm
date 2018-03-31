package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**支出的大分类*/
@Getter@Setter
@JsonIgnoreProperties(value = {"handler"})
public class MaxType extends BaseDomain{

    private String name;

    //与小分类的关系
    List<MinType> minTypes=new ArrayList<>();

}