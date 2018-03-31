package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SystemLog;
import cn.wolfcode.crm.mapper.SystemLogMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemLogService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class SystemLogServiceImpl implements ISystemLogService {

    @Autowired
    private SystemLogMapper systemLogMapper;

    @Override
    public int insert(SystemLog entity) {
        return systemLogMapper.insert(entity);
    }

    @Override
    public List<SystemLog> selectAll() {

        return systemLogMapper.selectAll();
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = systemLogMapper.queryCount(qo);
        if (total >= 1000){
            systemLogMapper.deleteAll();
        }
        List<SystemLog> data = systemLogMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
}
