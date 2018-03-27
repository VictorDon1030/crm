package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Pay;

import java.util.List;

public interface IPayService {
    void delete(Long id);

    void insert(Pay entity);

    Pay selectByPrimaryKey(Long id);

    List<Pay> selectAll();

    void update(Pay entity);
}
