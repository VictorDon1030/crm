package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.ClassAnalysisMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IClassAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ClassAnalysisServiceImpl implements IClassAnalysisService {

    @Autowired
    private ClassAnalysisMapper classAnalysisMapper;

    @Override
    public List<Map<String, Object>> selectAll() {
        return classAnalysisMapper.selectAll();
    }

    /**
     * 根据日期查询产品及其销售信息
     * @param qo
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByDate(QueryObject qo) {
        return classAnalysisMapper.queryByDate(qo);
    }

    /**
     * 高级查询并排序：柱状图用
     */
    public  List<Map<String,Object>> selectAndOrder(){
        return classAnalysisMapper.selectAndOrder();
    }
}
