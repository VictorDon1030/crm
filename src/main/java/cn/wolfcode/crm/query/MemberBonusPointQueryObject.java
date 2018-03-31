package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBonusPointQueryObject {
    private String keyword;

    public String getKeyword() {
        return StringUtil.empty2null(keyword);
    }
}
