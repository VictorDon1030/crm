package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.MinType;

import java.util.List;

public interface IMinTypeService {
    void delete(Long id);

    void insert(MinType entity);

    MinType selectByPrimaryKey(Long id);

    List<MinType> selectAll();

    void update(MinType entity);
}
