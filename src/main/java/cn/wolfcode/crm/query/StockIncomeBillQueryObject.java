package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockIncomeBillQueryObject extends QueryObject{

    private Long depotId=-1L;
    private int status = -1;
}
