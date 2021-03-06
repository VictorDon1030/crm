package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.BonusPointRecordMapper;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMemberAnalyzeService;
import cn.wolfcode.crm.service.IMemberService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private BonusPointRecordMapper bonusPointRecordMapper;
    @Autowired
    private IMemberAnalyzeService memberAnalyzeService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return memberMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Member entity) {
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        entity.setOnUser(employee);
        return memberMapper.insert(entity);
    }

    @Override
    public Member selectByPrimaryKey(Long id) {
        return memberMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Member> selectAll() {
        return memberMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Member entity) {
        return memberMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = memberMapper.queryCount(qo);
        List<Member> data = memberMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void changeState(Long id) {
        memberMapper.changeState(id);
    }

    @Override
    public List<Member> queryByKeyword(MemberBonusPointQueryObject qo) {
        return memberMapper.queryByKeyword(qo);
    }

    /**
     * 清除会员积分的方法
     *
     * @param id 要清除积分的会员的id
     */
    @Override
    public void clearPoints(Long id) {
        Employee optUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        Member member = memberMapper.selectByPrimaryKey(id);

        if (BigDecimal.ZERO.equals(member.getPoints())) {
            throw new RuntimeException("当前会员的积分已经为0,无需重复操作");
        }
        BonusPointRecord bonusPointRecord = new BonusPointRecord();
        bonusPointRecord.setAmount(BigDecimal.ZERO.subtract(member.getPoints()));
        bonusPointRecord.setOptDate(new Date());
        bonusPointRecord.setOptUser(optUser);
        bonusPointRecord.setMember(member);
        bonusPointRecord.setType(0);
        bonusPointRecord.setRemark("积分清零");
        bonusPointRecordMapper.insert(bonusPointRecord);
        memberMapper.clearPoints(id);
    }


    /**
     * 校验会员密码的方法
     *
     * @param password
     */
    @Override
    public void checkPass(String password,Long id) {
       Member member = memberMapper.checkPass(password,id);
        if (member == null) {
            throw new RuntimeException("密码不正确,请确定后重新输入");
        }
    }

    @Override
    public void checkPoints(Integer points,Long id) {
       Integer point = memberMapper.selectPoints(id);
      if(point < points) {
          throw new RuntimeException("对不起,兑换失败!当前会员积分余额"+point+"不足"+points+",请确定后重试!");
      }
    }

    @Override
    public void updatePasswordById(Member member) {
        Md5Hash md5Hash = new Md5Hash(member.getPassword(), member.getName(), 2);
        /*if(entity.getPassword()!= null) {
                Md5Hash md5Hash = new Md5Hash(entity.getPassword(), entity.getUsername(), 2);
                entity.setPassword(md5Hash.toString());
            }*/
        member.setPassword(md5Hash.toString());
        memberMapper.updatePasswordById(member);
    }

    @Override
    public Map<String,Object> selectMemberMsg() {
        /**
         * 查询今日生日
         * 查询这个月的生日
         * 查询总余额和总会员数
         */
        List<Map<String,Object>> list = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        //查询生日
        map.putAll(memberMapper.selectTodayBirthdayMember());
        List<Map<String, Object>> maps = memberAnalyzeService.selectAll();
        for (Map<String, Object> objectMap : maps) {
            map.putAll(objectMap);
        }
        /*消费排行榜*/
        List<Map<String, Object>> memberRankings =   memberAnalyzeService.selectsaleRanking();
        //查询总余额,总会员数
        map.putAll(memberMapper.selectSumMember());
        //查询今月生日
        map.putAll(memberMapper.selectMonthBirthdayMember());



        return map;
    }

}
