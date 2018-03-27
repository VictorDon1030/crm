package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DictionaryItem entity);

    DictionaryItem selectByPrimaryKey(Long id);

    List<DictionaryItem> selectAll();

    int updateByPrimaryKey(DictionaryItem entity);

    List<DictionaryItem> query4List(QueryObject qo);

    int query4Count(QueryObject qo);

    List<DictionaryItem> selectItemByDictionaryId(Long dictionaryId);

    void deleteRelation(Long dictionaryId);
}