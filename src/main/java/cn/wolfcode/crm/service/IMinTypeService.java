package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.MinType;

import java.util.List;

public interface IMinTypeService {
    void delete(Long id);

    void insert(MinType entity);

    MinType selectByPrimaryKey(Long id);

    List<MinType> selectAll();

    void update(MinType entity);

    /**
     * 根据支出大分类的id查出其所有的支出小分类
     * @param maxTypeId
     * @return
     */
    List<MinType> selectByMaxTypeId(Long maxTypeId);
}
