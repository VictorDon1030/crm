package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.domain.Member;
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

    void updatePasswordById(Member member);

    void updateBalance(@Param("balance") BigDecimal balance,@Param("mumberId") Long id);

    void updatePoints(@Param("points") BigDecimal points,@Param("mumberId") Long id);
}