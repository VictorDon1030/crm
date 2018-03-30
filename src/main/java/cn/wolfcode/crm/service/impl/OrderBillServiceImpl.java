package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.OrderBill;
import cn.wolfcode.crm.domain.OrderBillItem;
import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.mapper.OrderBillItemMapper;
import cn.wolfcode.crm.mapper.OrderBillMapper;
import cn.wolfcode.crm.mapper.ProductStockMapper;
import cn.wolfcode.crm.query.OrderBillQueryObject;
import cn.wolfcode.crm.service.IOrderBillService;
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
public class OrderBillServiceImpl implements IOrderBillService {
    @Autowired
    private OrderBillMapper orderBillMapper;
    @Autowired
    private OrderBillItemMapper orderBillItemMapper;
    @Autowired
    private ProductStockMapper productStockMapper;

    public void saveOrUpdate(OrderBill entity) {
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
        List<OrderBillItem> items = entity.getItems();

        for (OrderBillItem item : items) {
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
            orderBillMapper.insert(entity);
        }else {
            //更新前先删除之前的明细
            orderBillItemMapper.deleteByBillId(entity.getId());
            orderBillMapper.updateByPrimaryKey(entity);
        }
        //保存明细信息
        for (OrderBillItem item : items) {
            item.setAmount(item.getNumber().multiply(item.getCostPrice()));
            item.setBillId(entity.getId());
            //保存明细
            orderBillItemMapper.insert(item);
        }
    }

    public void delete(Long id) {
        if (id != null) {
            orderBillMapper.deleteByPrimaryKey(id);
            //删除明细
            orderBillItemMapper.deleteByBillId(id);
        }
    }

    public OrderBill get(Long id) {
        return orderBillMapper.selectByPrimaryKey(id);
    }

    public List<OrderBill> list() {
        return orderBillMapper.selectAll();
    }

    public PageResult query(OrderBillQueryObject qo) {
        int total = this.orderBillMapper.query4Count(qo);
        List<OrderBill> data = this.orderBillMapper.query4List(qo);
        return total == 0 ? new PageResult(total, Collections.EMPTY_LIST) : new PageResult(total, data);
    }


    //审核方法
    public void audit(Long id) {
        //查询出审核之前的单据
        OrderBill orderBill = orderBillMapper.selectByPrimaryKey(id);
        if (orderBill.getStatus() == OrderBill.AUDIT_UNTREATED) {
            orderBill.setStatus(OrderBill.AUDIT_PASS);
            orderBill.setAuditor((Employee) SecurityUtils.getSubject().getPrincipal());
            orderBill.setAuditTime(new Date());
            orderBillMapper.audit(orderBill);
        }
        //审核时还要更新或者插入库存表的数据
        List<OrderBillItem> items = orderBill.getItems();
        for (OrderBillItem item : items) {
            ProductStock productStock = productStockMapper.selectByProductIdDepotId(item.getProduct().getId(),
                    orderBill.getDepot().getId());
            //ProductStock ps = new ProductStock();
            if (productStock == null) {
                productStock = new ProductStock();
                //增加数据
                productStock.setStoreNumber(orderBill.getTotalNumber());
                productStock.setPrice(item.getCostPrice());
                productStock.setAmount(orderBill.getTotalAmount());
                productStock.setAuditTime(orderBill.getAuditTime());
                productStock.setProduct(item.getProduct());
                productStock.setDepot(orderBill.getDepot());
                productStockMapper.insert(productStock);
            } else {
                //更新数据
                productStock.setStoreNumber(orderBill.getTotalNumber().add(productStock.getStoreNumber()));
                productStock.setAmount(orderBill.getTotalAmount().add(productStock.getAmount()));
                productStock.setPrice(productStock.getAmount().divide(productStock.getStoreNumber(), 2, BigDecimal.ROUND_HALF_UP));
                productStock.setAuditTime(orderBill.getAuditTime());
                productStockMapper.updateByPrimaryKey(productStock);
            }

        }
    }
}
