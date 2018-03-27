package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Pay;
import java.util.List;

public interface PayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Pay entity);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    int updateByPrimaryKey(Pay entity);
}