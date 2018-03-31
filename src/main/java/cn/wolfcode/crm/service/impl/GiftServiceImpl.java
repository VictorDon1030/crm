package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.mapper.GiftMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IGiftService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.en.CalendarData_en;

import java.util.Collections;
import java.util.List;

@Service
public class GiftServiceImpl implements IGiftService {
    @Autowired
    private GiftMapper giftMapper;

    /**
     * 保存和更新的方法
     *
     * @param entity 要跟新或保存的gift实体对象
     */
    @Override
    public void saveOrUpdate(Gift entity) {
        entity.setTotalQuantity(entity.getInventory());
        if (entity.getId() == null) {
            giftMapper.insert(entity);
        } else {
//            Gift gift = giftMapper.selectByPrimaryKey(entity.getId());
//            entity.setTotalQuantity(gift.getTotalQuantity() + entity.getInventory());
            giftMapper.updateByPrimaryKey(entity);
        }

    }

    /**
     * 删除指定的记录
     *
     * @param id 要删除的记录的id
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Long id) {
        return giftMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询指定的礼品的信息
     *
     * @param id 要查询的礼品的id
     * @return 指定的礼品信息
     */
    @Override
    public Gift selectByPrimaryKey(Long id) {
        return giftMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有的礼品信息
     *
     * @return 所有的礼品信息
     */
    @Override
    public List<Gift> selectAll() {
        return giftMapper.selectAll();
    }

    /**
     * 分页操作
     *
     * @param qo
     * @return
     */
    @Override
    public PageResult query(QueryObject qo) {
        int total = giftMapper.query4count(qo);
        if (total == 0) {
            return new PageResult(0, Collections.EMPTY_LIST);
        }
        List<Gift> rows = giftMapper.query4list(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void updateInventory(Long id,Integer number) {
        giftMapper.updateInventory(id,number);
    }
}
