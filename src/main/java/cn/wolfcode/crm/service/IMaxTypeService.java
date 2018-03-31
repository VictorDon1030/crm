package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.MaxType;

import java.util.List;

public interface IMaxTypeService {
    void delete(Long id);

    void insert(MaxType entity);

    MaxType selectByPrimaryKey(Long id);

    List<MaxType> selectAll();

    void update(MaxType entity);
}
