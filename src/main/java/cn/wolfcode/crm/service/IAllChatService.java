package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.BonusPointRecord;

import java.util.List;
import java.util.Map;

/**
 * 对积分变动进行业务处理的接口
 */
public interface IAllChatService {

    List<Map<String,Object>> selectAllStair();

    List<Map<String,Object>> selectAllAmount();

    List<Map<String,Object>> selectAllProfit();
}
