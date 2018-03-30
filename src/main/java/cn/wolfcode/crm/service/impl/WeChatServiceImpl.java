package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.WeChat;
import cn.wolfcode.crm.mapper.WeChatMapper;
import cn.wolfcode.crm.service.IWeChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class WeChatServiceImpl implements IWeChatService {

    @Autowired
    private WeChatMapper weChatMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return weChatMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(WeChat entity) {
        return weChatMapper.insert(entity);
    }

    @Override
    public WeChat selectByPrimaryKey(Long id) {
        return weChatMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WeChat> selectAll() {
        return weChatMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(WeChat entity) {
        return weChatMapper.updateByPrimaryKey(entity);
    }
}
