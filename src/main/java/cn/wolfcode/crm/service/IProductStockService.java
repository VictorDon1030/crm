package cn.wolfcode.crm.service;


import cn.wolfcode.crm.domain.InventoryItem;
import cn.wolfcode.crm.domain.ProductStock;
import cn.wolfcode.crm.query.InventoryQueryObject;
import cn.wolfcode.crm.query.ProductStockQueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

public interface IProductStockService {
    PageResult query(ProductStockQueryObject qo);
    List<ProductStock> selectAll();
    PageResult selectProductStockByDepotId(ProductStockQueryObject qo);
    void updateInventoryTime(ProductStock entity);
    void updateStoreNumber(ProductStock entity);
    //查看盘点记录
    PageResult listAll(InventoryQueryObject qo);
    List<InventoryItem> selectAllItems();
    //查询所有的已审核的库存信息
    PageResult queryNoaudit(ProductStockQueryObject qo);

}
