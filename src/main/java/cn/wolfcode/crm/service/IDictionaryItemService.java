package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IDictionaryItemService {

    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem entity);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    int updateByPrimaryKey(DictionaryItem entity);

    int saveOrUpdate(DictionaryItem entity);

    PageResult query(QueryObject qo);


    List<DictionaryItem> selectItemByDictionaryId(Long DictionaryId);
}
