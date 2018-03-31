package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Secondary;
import cn.wolfcode.crm.domain.Stair;

import java.util.List;

/**
 * Created by China on 2018/3/3.
 */
public interface IStairService {

    void saveOrUpdate(Stair entity);

    int delete(Long id);

    Stair get(Long id);

    List<Stair> listAll();

    List<Secondary> getStairBySecondaryId(Long id);

}
