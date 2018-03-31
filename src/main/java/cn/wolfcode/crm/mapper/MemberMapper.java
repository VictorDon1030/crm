package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface MemberMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Member entity);

    Member selectByPrimaryKey(Long id);

    List<Member> selectAll();

    int updateByPrimaryKey(Member entity);

    int queryCount(QueryObject qo);

    List<Member> queryForList(QueryObject qo);

    void changeState(Long id);


    /**
     * 改变会员的积分余额
     * @param id 会员的id
     */
    void changePoint(@Param("id") Long id, @Param("amount") BigDecimal amount);
    /**
     * 从数据库进行关键字查询
     * @param qo
     * @return
     */
    List<Member> queryByKeyword(MemberBonusPointQueryObject qo);

    void clearPoints(Long id);

    Member checkPass(@Param("password") String password,@Param("id") Long id);

    Integer selectPoints(Long id);

    void updateConsumePoints(@Param("id") Long id, @Param("ret") Integer ret);

    void updatePasswordById(Member member);

    void updateBalance(@Param("balance") BigDecimal balance,@Param("mumberId") Long id);

    void updatePoints(@Param("points") BigDecimal points,@Param("mumberId") Long id);
}