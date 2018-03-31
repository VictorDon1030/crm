package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.ProductAnalyzeMapper;
import cn.wolfcode.crm.query.ProductAnalyzeObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IProductAnalyzeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ProductAnalyzeServiceImpl implements IProductAnalyzeService {

    @Autowired
    private ProductAnalyzeMapper productAnalyzeMapper;

    /**
     * 查出所有
     * @return
     */
    @Override
    public List<Map<String, Object>> selectAll() {
        return productAnalyzeMapper.selectAll();
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByDate(QueryObject qo) {
        return productAnalyzeMapper.queryByDate(qo);
    }

    /**
     * 高级查询并排序：柱状图用
     */
    public  List<Map<String,Object>> selectAndOrder(ProductAnalyzeObject qo){
        return productAnalyzeMapper.selectAndOrder(qo);
    }
}
