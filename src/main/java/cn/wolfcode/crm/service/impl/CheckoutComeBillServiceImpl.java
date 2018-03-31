package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.*;
import cn.wolfcode.crm.mapper.*;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICheckoutComeBillService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
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
    private ProductStockMapper productStockMapper;
    @Autowired
    private SaleasCountMapper saleasCountMapper;
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
        //查询挂单会员的销售单
        List<CheckoutComeBill> memlist = checkoutComeBillMapper.selectWaitPayment4MemberId(checkoutComeBill.getMember().getId());
        if(memlist!=null){
            //删除已结算的挂单
            checkoutComeBillMapper.deleteByMemberId(checkoutComeBill.getMember().getId());
            //删除已结算的挂单明细
            for (CheckoutComeBill comeBill : memlist) {
                checkoutComeBillItemMapper.deleteWaitPaymentItem(comeBill.getId());
            }
        }
        //保存时保存录入人的信息,由登录的人来获取录入人的id
        /*d.setInputUser(UserContext.getCurrenUser());
        d.setInputTime(new Date());*/
        BigDecimal totalNumber = BigDecimal.ZERO;
        BigDecimal totalAmout = BigDecimal.ZERO;
        //获取集合里的数量,和总价格set到销售表单中
        List<CheckoutComeBillItem> items = checkoutComeBill.getItems();
        if (items != null) {
            for (int i = 0; i < items.size() - 1; i++) {
                totalNumber = totalNumber.add(items.get(i).getNumber());
                totalAmout = totalAmout.add(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
            }
        }
        checkoutComeBill.setTotalAmount(totalAmout);
        checkoutComeBill.setTotalNumber(totalNumber);
        if (checkoutComeBill.getId() == null) {
            checkoutComeBillMapper.insert(checkoutComeBill);
            //修改会员的余额
            Long id = checkoutComeBill.getMember().getId();
            Member member = memberMapper.selectByPrimaryKey(id);
            //判断余额是否充足
            if (member.getBalance().compareTo(checkoutComeBill.getTotalAmount()) > 0) {
                //修改余额
                BigDecimal balance = member.getBalance().subtract(checkoutComeBill.getTotalAmount());
                memberMapper.updateBalance(balance, id);
                //修改积分
                BigDecimal points = checkoutComeBill.getMember().getPoints().add((checkoutComeBill.getTotalAmount().subtract(checkoutComeBill.getTotalAmount().divideAndRemainder(new BigDecimal(10))[1])).multiply(new BigDecimal(0.1)));
                memberMapper.updatePoints(points,id);

            } else {
                /*throw new ProductExceptions("商品"+item.getProduct().getName()+"在仓库"+stockOutcomeBill.getDepot().getName()+"不存在");*/
            }

            //修改productStock的数据
            if (items != null) {
                for (int i = 0; i < items.size() - 1; i++) {
                    /*totalNumber = totalNumber.add(items.get(i).getNumber());
                    totalAmout = totalAmout.add(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));*/
                    Long pid = items.get(i).getProduct().getId();
                    List<ProductStock> list = productStockMapper.selectAll();
                    for (ProductStock productStock : list) {
                        if (productStock.getDepot().getId() == 1) {
                            if (productStock.getProduct().getId() == pid) {
                                if (productStock.getStoreNumber().compareTo(new BigDecimal(0)) > 0) {
                                    BigDecimal number = productStock.getStoreNumber().subtract(items.get(i).getNumber());
                                    BigDecimal amount = productStock.getPrice().multiply(number);
                                    productStockMapper.updateNumber(number, amount, pid);
                                }
                            }
                        }
                    }
                }
            }

            //生成销售帐
            if (items != null) {
                for (int i = 0; i < items.size() - 1; i++) {

                    SaleasCount sale = new SaleasCount();
                    sale.setMember(checkoutComeBill.getMember());
                    sale.setCostPrice(items.get(i).getProduct().getPurchasingPrice());
                    sale.setNumber(checkoutComeBill.getTotalNumber());
                    sale.setCostAmount(sale.getCostPrice().multiply(sale.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    sale.setSalePrice(items.get(i).getUnitpPrice());
                    sale.setSaleAmount(sale.getSalePrice().multiply(sale.getNumber()).setScale(2, BigDecimal.ROUND_HALF_UP));
                    sale.setProduct(items.get(i).getProduct());
                    sale.setSaleman(checkoutComeBill.getSalesman());
                    sale.setVdate(new Date());
                    saleasCountMapper.insert(sale);
                }
            }
        } else {
            checkoutComeBillMapper.updateByPrimaryKey(checkoutComeBill);
        }
        if (items != null) {
            for (int i = 0; i < items.size() - 1; i++) {
                //明细表里的总价
                items.get(i).setAmount(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
                items.get(i).setBillId(checkoutComeBill.getId());
                checkoutComeBillItemMapper.insert(items.get(i));
            }
        }
    }

    @Override
    public void waitPayment(CheckoutComeBill checkoutComeBill) {
        if(checkoutComeBill!=null) {
            BigDecimal totalNumber = BigDecimal.ZERO;
            BigDecimal totalAmout = BigDecimal.ZERO;
            //获取集合里的数量,和总价格set到销售表单中
            List<CheckoutComeBillItem> items = checkoutComeBill.getItems();
            if (items != null) {
                for (int i = 0; i < items.size() - 1; i++) {
                    totalNumber = totalNumber.add(items.get(i).getNumber());
                    totalAmout = totalAmout.add(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
                }
            }
            checkoutComeBill.setTotalAmount(totalAmout);
            checkoutComeBill.setTotalNumber(totalNumber);
            checkoutComeBillMapper.insert(checkoutComeBill);
            //保存明细
            if (items != null) {
                for (int i = 0; i < items.size() - 1; i++) {
                    //明细表里的总价
                    items.get(i).setAmount(items.get(i).getUnitpPrice().multiply(items.get(i).getNumber()));
                    items.get(i).setBillId(checkoutComeBill.getId());
                    items.get(i).setMember(checkoutComeBill.getMember());
                    checkoutComeBillItemMapper.insert(items.get(i));
                }
            }
            //修改为挂单状态
            checkoutComeBillMapper.updatestatus(checkoutComeBill);
        }
    }

    @Override
    public List<CheckoutComeBill> selectWaitPaymentMember() {
        List<CheckoutComeBill> checkoutComeBills = checkoutComeBillMapper.selectWaitPaymentMember();
        return checkoutComeBills;
    }

    @Override
    public List<CheckoutComeBillItem> selectbyMemberId(Long memberId) {
        List<CheckoutComeBillItem> list = checkoutComeBillItemMapper.selectbyMemberId(memberId);
        return list;
    }

    @Override
    public Long addOdd(String sn) {
        CheckoutComeBill checkoutComeBill = checkoutComeBillMapper.selectSn(sn);
        Long odds = checkoutComeBillItemMapper.selectCount();
        Long odd = checkoutComeBillMapper.selectSnSetOdd();
        if(checkoutComeBill!=null){
            return 200010L+odd+2000L+15*odds;
        }
        return 200010L+odd+1000+13*odds;
    }


}
