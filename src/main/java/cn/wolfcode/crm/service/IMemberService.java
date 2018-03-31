package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;
import java.util.Map;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IMemberService {

    int deleteByPrimaryKey(Long id);

    int insert(Member entity);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member entity);

    PageResult query(QueryObject qo);

    /**
     * 修改会员挂失的状态
     * @param id
     */
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
    /**
     *  修改会员密码的操作
     * @param member
     */
    void updatePasswordById(Member member);

    /**
     * 查询生日,人数,总额
     * @return
     */
    List<Map<String,Object>> selectMemberMsg();
}
