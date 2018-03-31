package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import cn.wolfcode.crm.domain.CheckoutComeBillItem;
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

    void waitPayment(CheckoutComeBill checkoutComeBill);

    /**
     * 查询出挂单会员
     * @return
     */
    List<CheckoutComeBill> selectWaitPaymentMember();

    /**
     * 查出该会员挂单商品的所有明细
     * @param memberId
     * @return
     */
    List<CheckoutComeBillItem> selectbyMemberId(Long memberId);

    /**
     *
     * @param
     * @return
     */
    Long addOdd(String sn);
}
