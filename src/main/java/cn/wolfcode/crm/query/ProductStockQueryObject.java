package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductStockQueryObject extends QueryObject {
    private String keyword;
    private Integer depotId=-1;

    private Long warnNum;
}
