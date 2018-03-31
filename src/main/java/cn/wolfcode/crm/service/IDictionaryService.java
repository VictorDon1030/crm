package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Dictionary;
import cn.wolfcode.crm.domain.DictionaryItem;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IDictionaryService {

    int deleteByPrimaryKey(Long id);

    int insert(Dictionary entity);

    Dictionary selectByPrimaryKey(Long id);

    List<Dictionary> selectAll();

    int updateByPrimaryKey(Dictionary entity);

    int saveOrUpdate(Dictionary entity);

    PageResult query(QueryObject qo);


    void changeState(Long id);
}
