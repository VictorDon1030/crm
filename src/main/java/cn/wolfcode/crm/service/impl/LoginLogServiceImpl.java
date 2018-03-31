package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.LoginLog;
import cn.wolfcode.crm.mapper.LoginLogMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ILoginLogService;
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
public class LoginLogServiceImpl implements ILoginLogService {

    @Autowired
    private LoginLogMapper loginLogMapper;

    @Override
    public int insert(LoginLog entity) {
        return loginLogMapper.insert(entity);
    }

    @Override
    public List<LoginLog> selectAll() {
        return loginLogMapper.selectAll();
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = loginLogMapper.queryCount(qo);
        List<LoginLog> data = loginLogMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
}
