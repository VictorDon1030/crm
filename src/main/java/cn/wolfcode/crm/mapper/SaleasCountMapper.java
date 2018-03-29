package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SaleasCount;
import java.util.List;

public interface SaleasCountMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SaleasCount entity);

    SaleasCount selectByPrimaryKey(Long id);

    List<SaleasCount> selectAll();

    int updateByPrimaryKey(SaleasCount entity);
}