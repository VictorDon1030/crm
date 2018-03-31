package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Refund;
import cn.wolfcode.crm.domain.RefundItem;
import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.mapper.RefundItemMapper;
import cn.wolfcode.crm.mapper.RefundMapper;
import cn.wolfcode.crm.mapper.ProductStockMapper;
import cn.wolfcode.crm.query.RefundQueryObject;
import cn.wolfcode.crm.service.IRefundService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class RefundServiceImpl implements IRefundService {
    @Autowired
    private RefundMapper refundMapper;
    @Autowired
    private RefundItemMapper refundItemMapper;
    @Autowired
    private ProductStockMapper productStockMapper;

    public void saveOrUpdate(Refund entity) {
        //封装录入人
        entity.setInputUser((Employee) SecurityUtils.getSubject().getPrincipal());
        String s = String.valueOf(Calendar.getInstance().getTime().getTime());
        entity.setSn(s);
        //封装录入时间
        entity.setInputTime(new Date());
        //先设置全局变量
        BigDecimal totalNumber = new BigDecimal(0);
        BigDecimal totalAmount = new BigDecimal(0);
        //从订单中获取明细集合对象
        List<RefundItem> items = entity.getItems();

        for (RefundItem item : items) {
            //明细的数量.遍历增加后赋给订单数量
            //明细的总价.为数量*售价  相加后赋给订单总计
            if(item.getNumber()==null){
                throw  new RuntimeException(",商品数量必填");
            }
            totalNumber=totalNumber.add(item.getNumber());
            totalAmount= totalAmount.add(item.getNumber().multiply(item.getCostPrice()));
        }
        //将有数据的字段,封装给对象
        entity.setTotalAmount(totalAmount);
        entity.setTotalNumber(totalNumber);
        if (entity.getId() == null) {
            //保存订单对象
            refundMapper.insert(entity);
        }else {
            //更新前先删除之前的明细
            refundItemMapper.deleteByRefundId(entity.getId());
            refundMapper.updateByPrimaryKey(entity);
        }
        //保存明细信息
        for (RefundItem item : items) {
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            item.setRefundId(entity.getId());
            //保存明细
            refundItemMapper.insert(item);
        }
    }

    public void delete(Long id) {
        if (id != null) {
            refundMapper.deleteByPrimaryKey(id);
            //删除明细
            refundItemMapper.deleteByRefundId(id);
        }
    }

    public Refund get(Long id) {
        return refundMapper.selectByPrimaryKey(id);
    }

    public List<Refund> list() {
        return refundMapper.selectAll();
    }

    public PageResult query(RefundQueryObject qo) {
        int total = this.refundMapper.query4Count(qo);
        List<Refund> data = this.refundMapper.query4List(qo);
        return total == 0 ? new PageResult(total, Collections.EMPTY_LIST) : new PageResult(total, data);
    }


    //审核方法
    public void audit(Long id) {
        //查询出审核之前的单据
        Refund refund = refundMapper.selectByPrimaryKey(id);
        if (refund.getStatus() == Refund.AUDIT_UNTREATED) {
            refund.setStatus(Refund.AUDIT_PASS);
            refund.setAuditor((Employee) SecurityUtils.getSubject().getPrincipal());
            refund.setAuditTime(new Date());
            refundMapper.audit(refund);
        }
        //审核时还要更新或者插入库存表的数据
        List<RefundItem> items = refund.getItems();
        for (RefundItem item : items) {
            ProductStock productStock = productStockMapper.selectByProductIdDepotId(item.getProduct().getId(),
                    refund.getDepot().getId());
            //更新数据
            if (productStock!=null){
                productStock.setStoreNumber(productStock.getStoreNumber().subtract(refund.getTotalNumber()));
                productStock.setAmount(productStock.getAmount().subtract(refund.getTotalAmount()));
                productStock.setPrice(productStock.getAmount().divide(productStock.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
                productStockMapper.updateByPrimaryKey(productStock);
            }


        }
    }
}
