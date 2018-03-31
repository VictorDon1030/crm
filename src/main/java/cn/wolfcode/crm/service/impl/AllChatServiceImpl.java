package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.LoginLog;
import cn.wolfcode.crm.mapper.AllChatMapper;
import cn.wolfcode.crm.mapper.LoginLogMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IAllChatService;
import cn.wolfcode.crm.service.ILoginLogService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class AllChatServiceImpl implements IAllChatService {

    @Autowired
    private AllChatMapper allChatMapper;

    @Override
    public List<Map<String, Object>> selectAllStair() {
        return allChatMapper.selectAllStair();
    }

    @Override
    public List<Map<String, Object>> selectAllAmount() {
        return allChatMapper.selectAllAmount();
    }

    @Override
    public List<Map<String, Object>> selectAllProfit() {
        return allChatMapper.selectAllProfit();
    }
}
