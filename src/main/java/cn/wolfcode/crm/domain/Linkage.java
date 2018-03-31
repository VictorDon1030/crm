package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Linkage extends BaseDomain{
    private String text; //菜单名称

    private String url; //菜单路径

    //上级菜单
    private Linkage parent;

    //子菜单 children 根据easyUI属性名来创建子菜单
    private List<Linkage> children = new ArrayList<>();

    //菜单关联的权限
    private Permission permission;

}