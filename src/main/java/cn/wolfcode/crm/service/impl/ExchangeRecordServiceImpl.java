package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.ExchangeRecord;
import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.ExchangeRecordMapper;
import cn.wolfcode.crm.mapper.GiftMapper;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IExchangeRecordService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeRecordServiceImpl implements IExchangeRecordService {
    @Autowired
    private ExchangeRecordMapper exchangeRecordMapper;
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public PageResult query(QueryObject qo) {
        int total = exchangeRecordMapper.query4count(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<ExchangeRecord> rows = exchangeRecordMapper.query4list(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void save(ExchangeRecord entity) {
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        entity.setConsumeStore("德客便利店");
        entity.setExchangeDate(new Date());
        entity.setOptUser(employee);
        List<Member> members = entity.getMembers();
        exchangeRecordMapper.insert(entity);
        if (members != null) {
            for (Member member : members) {
                //维护中间表
                exchangeRecordMapper.insertRelation(member.getId(), entity.getId());
                memberMapper.changePoint(member.getId(),BigDecimal.ZERO.subtract(new BigDecimal(entity.getCostPoints())));
            }
        }
        //让会员的积分减少
    }
}
