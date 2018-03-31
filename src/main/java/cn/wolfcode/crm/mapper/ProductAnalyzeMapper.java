package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Pay;
import cn.wolfcode.crm.query.ProductAnalyzeObject;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

/**
 * 产品分析
 */
public interface ProductAnalyzeMapper {

    /**
     * 查出所有
     * @return
     */
    List<Map<String,Object>> selectAll();

    /**
     * 根据销售日期来查询产品及其销售信息
     * @param qo
     * @return
     */
    List<Map<String,Object>> queryByDate(QueryObject qo);

    /**
     * 高级查询并排序：柱状图用（按照销量排序）
     */
    List<Map<String,Object>> selectAndOrder(ProductAnalyzeObject qo);

    /**
     * 高级查询并排序：柱状图用（按照毛利排序）
     */
    List<Map<String,Object>> selectAndOrderByProfit(ProductAnalyzeObject qo);
}
