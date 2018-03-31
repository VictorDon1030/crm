package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.BonusPointRecord;

import java.util.List;
import java.util.Map;

public interface AllChatMapper {

    List<Map<String,Object>> selectAllStair();

    List<Map<String,Object>> selectAllAmount();

    List<Map<String,Object>> selectAllProfit();

    List<Map<String,Object>> selectAllProfitDesc();
}