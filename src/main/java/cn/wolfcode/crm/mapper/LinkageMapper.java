package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Linkage;

import java.util.List;

public interface LinkageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Linkage entity);

    Linkage selectByPrimaryKey(Long id);

    List<Linkage> selectAll();

    int updateByPrimaryKey(Linkage entity);

    List<Linkage> getRootLinkage();
}