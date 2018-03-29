package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import java.util.List;

public interface CheckoutComeBillMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckoutComeBill entity);

    CheckoutComeBill selectByPrimaryKey(Long id);

    List<CheckoutComeBill> selectAll();

    int updateByPrimaryKey(CheckoutComeBill entity);
}