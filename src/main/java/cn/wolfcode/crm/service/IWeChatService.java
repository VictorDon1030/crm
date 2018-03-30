package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.WeChat;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IWeChatService {

    int deleteByPrimaryKey(Long id);

    int insert(WeChat entity);

    WeChat selectByPrimaryKey(Long id);

    List<WeChat> selectAll();

    int updateByPrimaryKey(WeChat entity);

}
