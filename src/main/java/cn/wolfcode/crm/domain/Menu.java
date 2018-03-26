package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Menu extends BaseDomain {

    private String text;

    private String url;

    private Menu parent;
    private List<Menu> children;

    private String iconCls;

    private Permission permission;
}