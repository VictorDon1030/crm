package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class Depot extends BaseDomain {

    private String name;

    private String sn;
    //地址
    private String address;
    //状态
    private Boolean state;
    //明细介绍
    private String info;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date vdate;

    private Employee employee;
}