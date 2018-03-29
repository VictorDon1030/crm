package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.CheckoutComeBill;
import cn.wolfcode.crm.domain.CheckoutComeBillItem;
import cn.wolfcode.crm.domain.Member;
import cn.wolfcode.crm.mapper.CheckoutComeBillItemMapper;
import cn.wolfcode.crm.mapper.CheckoutComeBillMapper;
import cn.wolfcode.crm.mapper.MemberMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICheckoutComeBillService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class CheckoutComeBillServiceImpl implements ICheckoutComeBillService {

    @Autowired
    private CheckoutComeBillMapper checkoutComeBillMapper;
    @Autowired
    private CheckoutComeBillItemMapper checkoutComeBillItemMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    
    @Override
    public void deleteByPrimaryKey(Long id) {
         checkoutComeBillMapper.deleteByPrimaryKey(id);
    }

    @Override
    public CheckoutComeBill selectByPrimaryKey(Long id) {
        return checkoutComeBillMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<CheckoutComeBill> selectAll() {
        return checkoutComeBillMapper.selectAll();
    }

    @Override
    public void saveOrUpdate(CheckoutComeBill checkoutComeBill) {
        //保存时保存录入人的信息,由登录的人来获取录入人的id
        /*d.setInputUser(UserContext.getCurrenUser());
        d.setInputTime(new Date());*/
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmout = BigDecimal.ZERO;
        //获取集合里的数量,和总价格set到销售表单中
        List<CheckoutComeBillItem> items = checkoutComeBill.getItems();
        if(items!=null) {
            for (int i = 0; i < items.size() - 1; i++) {
                totalNumber = totalNumber.add(items.get(i).getNumber());
                totalAmout = totalAmout.add(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
            }
        }
        checkoutComeBill.setTotalAmount(totalAmout);
        checkoutComeBill.setTotalNumber(totalNumber);
        if(checkoutComeBill.getId()==null){
            checkoutComeBillMapper.insert(checkoutComeBill);
            //修改会员的余额
            Long id = checkoutComeBill.getMember().getId();
            Member member = memberMapper.selectByPrimaryKey(id);
            //判断余额是否充足
            if(member.getBalance().compareTo(checkoutComeBill.getTotalAmount())>0){
                BigDecimal balance = member.getBalance().subtract(checkoutComeBill.getTotalAmount());
                memberMapper.updateBalance(balance,id);
            }else{
                /*throw new ProductExceptions("商品"+item.getProduct().getName()+"在仓库"+stockOutcomeBill.getDepot().getName()+"不存在");*/
            }


        }else{
            checkoutComeBillMapper.updateByPrimaryKey(checkoutComeBill);
        }
        if(items!=null) {
            for (int i = 0; i < items.size() - 1; i++) {
                //明细表里的总价
                items.get(i).setAmount(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
                items.get(i).setBillId(checkoutComeBill.getId());
                checkoutComeBillItemMapper.insert(items.get(i));
            }
        }
    }



}
