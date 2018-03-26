package cn.wolfcode.crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {

    private int total;
    private List<?> rows = new ArrayList<>();
}
