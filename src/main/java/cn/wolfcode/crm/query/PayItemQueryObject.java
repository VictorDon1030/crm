package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Setter
@Getter
public class PayItemQueryObject extends QueryObject {
    private Long today;
    private Long week;
    private Long month;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd hhhh:mm:ss")
    private Date endDate;
}
