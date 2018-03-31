package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Secondary;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
public interface ISecondaryService {

    void saveOrUpdate(Secondary entity);

    int delete(Long id);

    Secondary get(Long id);

    List<Secondary> listAll();

}
