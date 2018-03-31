package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.WeChat;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface WeChatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WeChat entity);

    WeChat selectByPrimaryKey(Long id);

    List<WeChat> selectAll();

    int updateByPrimaryKey(WeChat entity);


    int queryCount(QueryObject qo);

    List<WeChat> queryForList(QueryObject qo);
}