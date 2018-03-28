package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
/**支出*/
@Getter@Setter
public class Pay extends BaseDomain{
    private String type;//支出类型：大类型-小类型
    private Long amount;//支出金额

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;//支出日期

    private String remark;//备注

}