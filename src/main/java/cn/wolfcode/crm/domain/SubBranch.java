package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
public class SubBranch extends BaseDomain {

    //分店名称
    private String shopName;

    //简介
    private String intro;

    //联系人
    private String linkman;

    //电话
    private String tel;

    //状态
    private Boolean state;

    //地址
    private String addr;

    //注册时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GTM+8")
    private Date joinDate;

    //微商城状态
    private Boolean mallState;

}