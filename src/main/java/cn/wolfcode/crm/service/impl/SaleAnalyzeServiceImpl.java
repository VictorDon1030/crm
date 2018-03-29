package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.SaleAnalyzeMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISaleAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SaleAnalyzeServiceImpl implements ISaleAnalyzeService {

    @Autowired
    private SaleAnalyzeMapper saleAnalyzeMapper;
    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByDate(QueryObject qo) {
        return saleAnalyzeMapper.queryByDate(qo);
    }

    /**
     * 高级查询并排序：柱状图用
     */
    public  List<Map<String,Object>> selectAndOrder(){
        return saleAnalyzeMapper.selectAndOrder();
    }
}
