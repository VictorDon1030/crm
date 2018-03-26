package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Setter
@Getter
public class QueryObject {
    private int page = 1;
    private int rows = 10;

    public int getStart(){
        return (page - 1)*rows;
    }
}
