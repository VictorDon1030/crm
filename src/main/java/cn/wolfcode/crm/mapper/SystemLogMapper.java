package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemLog;
import java.util.List;

public interface SystemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemLog entity);

    SystemLog selectByPrimaryKey(Long id);

    List<SystemLog> selectAll();

    int updateByPrimaryKey(SystemLog entity);
}