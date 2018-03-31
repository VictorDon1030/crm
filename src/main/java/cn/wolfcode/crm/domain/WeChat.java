package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WeChat extends BaseDomain {
    private String accountNumber;

    private String secretkey;

    private String applyKey;

    private JoinApply applyer;
}