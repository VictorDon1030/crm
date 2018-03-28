package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DictionaryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Dictionary entity);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary entity);


    List<Dictionary> query4List(QueryObject qo);

    int query4Count(QueryObject qo);


    void changeState(Long id);
}