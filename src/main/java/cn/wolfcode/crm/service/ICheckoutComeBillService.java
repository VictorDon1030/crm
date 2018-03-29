package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface ICheckoutComeBillService {

    void deleteByPrimaryKey(Long id);

    CheckoutComeBill selectByPrimaryKey(Long id);

    List<CheckoutComeBill> selectAll();

    void saveOrUpdate(CheckoutComeBill entity);

}
