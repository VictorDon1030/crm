package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CheckoutComeBillItem;
import java.util.List;

public interface CheckoutComeBillItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckoutComeBillItem entity);

    CheckoutComeBillItem selectByPrimaryKey(Long id);

    List<CheckoutComeBillItem> selectAll();

    int updateByPrimaryKey(CheckoutComeBillItem entity);

    void deleteByOrderId(Long billId);

    List<CheckoutComeBillItem> selectbyMemberId(Long memberId);

    /**
     * 删除客户的挂单明细
     * @param billId
     */
    void deleteWaitPaymentItem(Long billId);

    Long selectCount();
}