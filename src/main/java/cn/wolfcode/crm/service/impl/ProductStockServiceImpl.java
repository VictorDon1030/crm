package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.InventoryItem;
import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.mapper.InventoryItemMapper;
import cn.wolfcode.crm.mapper.ProductStockMapper;
import cn.wolfcode.crm.query.InventoryQueryObject;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.service.IProductStockService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ProductStockServiceImpl implements IProductStockService {
    @Autowired
    private ProductStockMapper productStockMapper;
    @Autowired
    private InventoryItemMapper inventoryItemMapper;
    //高级查询所有的库存
    public PageResult query(ProductStockQueryObject qo) {
        int total = productStockMapper.query4Count(qo);
        List<?> data = productStockMapper.query4List(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
    //高级查询所有的已审核的库存信息
    public PageResult queryNoaudit(ProductStockQueryObject qo) {
        int total = productStockMapper.query4Count(qo);
        List<?> data = productStockMapper.query4ListNoaudit(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
    @Override
    public List<ProductStock> selectAll() {
        return productStockMapper.selectAll();
    }

    @Override
    public PageResult selectProductStockByDepotId(ProductStockQueryObject qo) {
        int total = productStockMapper.selectProductStockByDepotId4Count(qo);
        List<?> data = productStockMapper.selectProductStockByDepotId(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }

    @Override
    public void updateInventoryTime(ProductStock entity) {
        if (entity.getId()!=null){
            entity.setInventoryTime(new Date());
            entity.setEmployee((Employee) SecurityUtils.getSubject().getPrincipal());
            productStockMapper.updateInventoryTime(entity);

            //保存记录
            ProductStock productStock = productStockMapper.selectByPrimaryKey(entity.getId());
            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setEmployee(productStock.getEmployee());
            inventoryItem.setInventoryTime(productStock.getInventoryTime());
            inventoryItem.setNewNumber(productStock.getStoreNumber());
            inventoryItem.setStoreNumber(productStock.getStoreNumber());
            inventoryItem.setProductStockeId(productStock.getId());
            inventoryItemMapper.insert(inventoryItem);
        }
    }

    @Override
    public void updateStoreNumber(ProductStock entity) {
        if (entity.getId()!=null){
            entity.setInventoryTime(new Date());
            entity.setEmployee((Employee) SecurityUtils.getSubject().getPrincipal());
            ProductStock productStock = productStockMapper.selectByPrimaryKey(entity.getId());
            entity.setAmount(entity.getNewNumber().multiply(productStock.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP));
            productStockMapper.updateStoreNumber(entity);
            //保存记录
            InventoryItem inventoryItem = new InventoryItem();
            inventoryItem.setEmployee(entity.getEmployee());
            inventoryItem.setInventoryTime(entity.getInventoryTime());
            inventoryItem.setNewNumber(entity.getNewNumber());
            inventoryItem.setStoreNumber(entity.getStoreNumber());
            inventoryItem.setProductStockeId(entity.getId());
            inventoryItemMapper.insert(inventoryItem);
        }
    }
    //根据库存id查询盘点明细
    @Override
    public PageResult listAll(InventoryQueryObject qo) {
        int total = inventoryItemMapper.query4Count(qo);
        List<?> data = inventoryItemMapper.query4List(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }
    //查询所有的盘点明细
    @Override
    public List<InventoryItem> selectAllItems(){
        return inventoryItemMapper.selectAll();
    }
}
