package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

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
     *  修改会员密码的操作
     * @param member
     */
    void updatePasswordById(Member member);
}
