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
public class JoinApplyQuery extends QueryObject {

    private Long appType = -1L;
    private String keyword;
}
