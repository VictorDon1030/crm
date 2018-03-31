package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import java.util.List;

public interface CheckoutComeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckoutComeBill checkoutComeBill);

    CheckoutComeBill selectByPrimaryKey(Long id);

    List<CheckoutComeBill> selectAll();

    int updateByPrimaryKey(CheckoutComeBill checkoutComeBill);

    void updatestatus(CheckoutComeBill checkoutComeBill);

    List<CheckoutComeBill> selectWaitPaymentMember();
    /**
     * 查找出固定会员的挂单
     * @return
     */
    List<CheckoutComeBill> selectWaitPayment4MemberId(Long memberId);
    /**
     * 结算后删除对应会员的挂单
     * @return
     */
    void deleteByMemberId(Long memberId);

    CheckoutComeBill selectSn(String sn);

    Long selectSnSetOdd();
}