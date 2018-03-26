package cn.wolfcode.crm.util;

import lombok.Getter;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Getter
public class JsonResult {
    private boolean success = true;
    private String msg;

    public void mark(String msg){
        this.success = false;
        this.msg = msg;
    }
}
