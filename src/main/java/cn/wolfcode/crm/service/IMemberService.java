package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 */
public interface IMemberService {

    int deleteByPrimaryKey(Long id);

    int insert(Member entity);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member entity);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    /**
     * 根据用户输入的关键字查询会员列表
     * @param qo
     * @return
     */
    List<Member> queryByKeyword(MemberBonusPointQueryObject qo);

    /**
     * 清除会员积分的方法
     * @param id 要清除积分的会员的id
     */
    void clearPoints(Long id);
}
