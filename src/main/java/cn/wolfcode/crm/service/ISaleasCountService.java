package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SaleasCount;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface ISaleasCountService {

    int deleteByPrimaryKey(Long id);

    int insert(SaleasCount entity);

    SaleasCount selectByPrimaryKey(Long id);

    List<SaleasCount> selectAll();

    int updateByPrimaryKey(SaleasCount entity);

    PageResult query(QueryObject qo);

}
