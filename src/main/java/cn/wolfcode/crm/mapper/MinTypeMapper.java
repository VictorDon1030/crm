package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.MinType;
import java.util.List;

public interface MinTypeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MinType entity);

    MinType selectByPrimaryKey(Long id);

    List<MinType> selectAll();

    int updateByPrimaryKey(MinType entity);

    /**
     * 根据支出大分类的id查出其所有的支出小分类
     * @param maxTypeId
     * @return
     */
    List<MinType> selectByMaxTypeId(Long maxTypeId);

    MinType selectByName(String minTypeName);
}