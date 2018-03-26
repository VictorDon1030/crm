package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.mapper.DictionaryItemMapper;
import cn.wolfcode.crm.mapper.DictionaryMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryServiceImpl implements IDictionaryService {

    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        /*打破和明细表的关系*/
        dictionaryItemMapper.deleteRelation(id);
        return dictionaryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Dictionary entity) {
        return 0;
    }

    @Override
    public Dictionary selectByPrimaryKey(Long id) {
        return dictionaryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Dictionary> selectAll() {
        return dictionaryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Dictionary entity) {
        return 0;
    }

    @Override
    public int saveOrUpdate(Dictionary entity) {
        if(entity.getId()==null){
            return dictionaryMapper.insert(entity);
        }else{
            return dictionaryMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {

        PageResult result = new PageResult();
        int count = dictionaryMapper.query4Count(qo);
        if (count==0) {
            return  new PageResult();
        }
        List<Dictionary> rows = dictionaryMapper.query4List(qo);
        result.setTotal(count);
        result.setRows(rows);
        return result;
    }


}
