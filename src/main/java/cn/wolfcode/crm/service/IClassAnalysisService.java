package cn.wolfcode.crm.service;

import cn.wolfcode.crm.query.QueryObject;

import java.util.List;
import java.util.Map;

public interface IClassAnalysisService {
    /**
     * 根据销售日期来查询产品及其销售信息
     * @param qo
     * @return
     */
    List<Map<String,Object>> queryByDate(QueryObject qo);

    /**
     * 高级查询并排序：柱状图用
     */
    List<Map<String,Object>> selectAndOrder();
}
