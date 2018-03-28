package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.BonusPointRecord;
import cn.wolfcode.crm.query.MemberBonusPointQueryObject;
import java.util.List;

public interface BonusPointRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BonusPointRecord entity);

    BonusPointRecord selectByPrimaryKey(Long id);

    List<BonusPointRecord> selectAll();

    int updateByPrimaryKey(BonusPointRecord entity);

    List<BonusPointRecord> selectByMemberId(Long memberId);

//    List<BonusPointRecord> queryByKeyword(MemberBonusPointQueryObject qo);

}