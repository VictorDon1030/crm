package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.mapper.MemberTopUpMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberTopUpService;
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
public class MemberTopUpServiceImpl implements IMemberTopUpService {

    @Autowired
    private MemberTopUpMapper memberTopUpMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return memberTopUpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberTopUp entity) {
        return memberTopUpMapper.insert(entity);
    }

    @Override
    public MemberTopUp selectByPrimaryKey(Long id) {
        return memberTopUpMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<MemberTopUp> selectAll() {
        return memberTopUpMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(MemberTopUp entity) {
        return memberTopUpMapper.updateByPrimaryKey(entity);
    }
    @Override
    public PageResult query(QueryObject qo) {
        int total = memberTopUpMapper.queryCount(qo);
        List<MemberTopUp> data = memberTopUpMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }


}
