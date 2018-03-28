package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.mapper.DictionaryItemMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDictionaryItemService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictionaryItemServiceImpl implements IDictionaryItemService {

    @Autowired
    private DictionaryItemMapper dictionaryItemMapper;


    @Override
    public int deleteByPrimaryKey(Long id) {
        return dictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(DictionaryItem entity) {
        return 0;
    }

    @Override
    public DictionaryItem selectByPrimaryKey(Long id) {
        return dictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<DictionaryItem> selectAll() {
        return dictionaryItemMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(DictionaryItem entity) {
        return 0;
    }

    @Override
    public int saveOrUpdate(DictionaryItem entity) {
        if(entity.getId()==null){
            return dictionaryItemMapper.insert(entity);
        }else{
            return dictionaryItemMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public PageResult query(QueryObject qo) {

        PageResult result = new PageResult();
        int count = dictionaryItemMapper.query4Count(qo);
        if (count==0) {
            return  new PageResult();
        }
        List<DictionaryItem> rows = dictionaryItemMapper.query4List(qo);
        result.setTotal(count);
        result.setRows(rows);
        return result;
    }

    /*根据字典id查询明细*/
    @Override
    public List<DictionaryItem> selectItemByDictionaryId(Long DictionaryId) {


        return dictionaryItemMapper.selectItemByDictionaryId(DictionaryId);
    }

    @Override
    public List<DictionaryItem> selectItemByDictionarySn(String dictionarySn) {
        return dictionaryItemMapper.dictionaryItemService(dictionarySn);
    }

    @Override
    public void changeState(Long id) {
        dictionaryItemMapper.changeState(id);
    }

    @Override
    public String selectById(Long id) {
        return dictionaryItemMapper.selectById(id);
    }


}
