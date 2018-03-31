package cn.wolfcode.crm.query;

import cn.wolfcode.crm.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keyword;
    private Integer depotId=-1;
    //仓库查看库存高级查询
    private Long depot_stockId;
    private Long warnNum;
    //盘点时间高级查询
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;
    public Date getEndDate(){
        return  endDate==null?null: DateUtil.getEndDate(endDate);
    }
}
