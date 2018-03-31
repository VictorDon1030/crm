package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.DateUtil;
import cn.wolfcode.crm.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ExchangeRecordQueryObject extends QueryObject{
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Date getEndDate() {
        return DateUtil.getEndDate(endDate);
    }

    public String getKeyword() {
        return StringUtil.empty2null(keyword);
    }
}
