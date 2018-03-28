package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;

import java.util.List;

/**
 * 对积分变动进行业务处理的接口
 */
public interface IBonusPointRecordService {

    /**
     * 保存或更新操作
     * @param entity 积分变动记录实体对象
     */
    void saveOrUpdate(BonusPointRecord entity);

    /**
     * 删除指定记录
     * @param id 指定记录的id
     * @return 受影响的行数
     */
    int deleteByPrimaryKey(Long id);


    /**
     * 查询指定的记录
     * @param id 指定记录的id
     * @return 要查询的对象
     */
    BonusPointRecord selectByPrimaryKey(Long id);

    /**
     * 查询所有的记录
     * @return 所有的记录
     */
    List<BonusPointRecord> selectAll();

    /**
     * 根据会员的id 查询会员的积分变化记录
     * @param memberId 会员的id
     * @return 会员的积分变化记录
     */
    List<BonusPointRecord> selectByMemberId(Long memberId);


    /**
     * 根据用户输入的搜索添加进行高级查询
     * @param qo 用户输入的关键字
     * @return
     */
//    List<BonusPointRecord> queryByKeyword(MemberBonusPointQueryObject qo);
}
