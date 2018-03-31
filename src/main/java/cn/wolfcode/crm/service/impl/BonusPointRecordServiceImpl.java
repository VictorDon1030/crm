package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.BonusPointRecordMapper;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.service.IBonusPointRecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Service
public class BonusPointRecordServiceImpl implements IBonusPointRecordService {
    //注入mapper对象
    @Autowired
    private BonusPointRecordMapper bonusPointRecordMapper;
    @Autowired
    private MemberMapper memberMapper;
    /**
     * 保存或更新操作
     *
     * @param entity 积分变动记录实体对象
     */
    @Override
    public void saveOrUpdate(BonusPointRecord entity) {
        Employee optUser = (Employee) SecurityUtils.getSubject().getPrincipal();
        if (entity.getId() == null) {
            entity.setOptUser(optUser);
            entity.setOptDate(new Date());
            //如果type等于1则让会员的积分增加 等于0 则将会员的积分减少
            if (entity.getType() == 0) {
                //获取当前会员的积分余额 进行判断如何小于要扣除的积分 则扣除失败

                Member member = memberMapper.selectByPrimaryKey(entity.getMember().getId());
                if (entity.getAmount().compareTo(member.getPoints()) == 1) {
                    throw new RuntimeException("当前会员"+member.getName()+"的积分余额"
                            + member.getPoints()+"不足"+entity.getAmount()+"扣除积分失败," +
                            "请确认后重新输入");
                }
                BigDecimal temp = BigDecimal.ZERO.subtract(entity.getAmount());
                entity.setAmount(temp);
                memberMapper.changePoint(entity.getMember().getId(),entity.getAmount());
            } else if(entity.getType() == 1) {
                memberMapper.changePoint(entity.getMember().getId(),entity.getAmount());
            }
            bonusPointRecordMapper.insert(entity);
        } else {
            bonusPointRecordMapper.updateByPrimaryKey(entity);
        }
    }

    /**
     * 删除指定记录
     *
     * @param id 指定记录的id
     * @return 受影响的行数
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return bonusPointRecordMapper.deleteByPrimaryKey(id);

    }

    /**
     * 查询指定的记录
     *
     * @param id 指定记录的id
     * @return 要查询的对象
     */
    @Override
    public BonusPointRecord selectByPrimaryKey(Long id) {
        return bonusPointRecordMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有的记录
     *
     * @return 所有的记录
     */
    @Override
    public List<BonusPointRecord> selectAll() {
        return bonusPointRecordMapper.selectAll();
    }

    /**
     * 根据会员的id 查询会员的积分变化记录
     *
     * @param memberId 会员的id
     * @return 会员的积分变化记录
     */
    @Override
    public List<BonusPointRecord> selectByMemberId(Long memberId) {
        return bonusPointRecordMapper.selectByMemberId(memberId);
    }

}
