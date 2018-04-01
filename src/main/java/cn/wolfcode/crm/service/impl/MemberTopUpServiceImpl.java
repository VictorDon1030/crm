package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.domain.MemberTopUp;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.mapper.MemberTopUpMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberTopUpService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.Date;
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
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return memberTopUpMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(MemberTopUp entity) {
        /*取出会员对象*/
        Member   member = memberMapper.selectByPrimaryKey(entity.getMember().getId());
        /*回去会员的金额*/
        BigDecimal decimal = new BigDecimal(BigInteger.ZERO);
/*比较充值的胆小,超过五千加500*/
        if (entity.getAddbalance().compareTo(new BigDecimal(5000))>=0) {
            //就赠送500
            entity.setGive(new BigDecimal(500));
        }
        decimal = decimal.add(entity.getAddbalance());
        //当赠送的金额null时,进行和充值的金额添加操作
        if (entity.getGive() != null) {
            decimal = decimal.add(entity.getGive());

        }
        /*添加会员的金额*/
        //当充值类型为1的时候,表示为充值状态
        if (entity.getAddway() == 1) {

            //当会员的余额部位null的时候,进行对充值的添加
            if (member.getBalance() != null) {
                member.setBalance(member.getBalance().add(decimal));

            } else {
                member.setBalance(decimal);
            }
            /*当为-1的时候,为扣费操作*/
        } else if (entity.getAddway() == -1) {
            member.setBalance( member.getBalance().subtract(decimal));
            /*最后为退还操作*/
        } else {
            member.setBalance( member.getBalance().subtract(decimal));
        }

        /*修改会员的金额*/
        memberMapper.updateByPrimaryKey(member);
        /*添加操作人*/
        Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
        entity.setOnUser(employee);
        //操作时间
        entity.setToptime(new Date());
        //添加充值明细
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
        if (total == 0) {
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public List<MemberTopUp> selecToptItemByMemberId(Long id) {
        return memberTopUpMapper.selecToptItemByMemberId(id);
    }


}
