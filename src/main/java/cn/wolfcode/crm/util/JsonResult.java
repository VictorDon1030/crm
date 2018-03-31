package cn.wolfcode.crm.util;

import lombok.Getter;
import lombok.Setter;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Getter
@Setter
public class JsonResult {
    private boolean success = true;
    private String msg;
   // private String data;

    public void mark(String msg){
        this.success = false;
        this.msg = msg;
    }
}
