package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class SystemLog extends BaseDomain {

    private Employee opUser;

    private Date opTime;

    private String opIp;

    private String function;

    private String params;
}