package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Gift;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * 对礼物列表进行增删改查的业务方法
 *
 * @author user
 */
public interface IGiftService {

    /**
     * 保存和更新的方法
     * @param entity 要跟新或保存的gift实体对象
     */
    void saveOrUpdate(Gift entity);

    /**
     * 删除指定的记录
     * @param id 要删除的记录的id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 查询指定的礼品的信息
     * @param id 要查询的礼品的id
     * @return 指定的礼品信息
     */
    Gift selectByPrimaryKey(Long id);

    /**
     * 查询所有的礼品信息
     * @return 所有的礼品信息
     */
    List<Gift> selectAll();

    /**
     * 分页操作
     * @param qo
     * @return
     */
    PageResult query(QueryObject qo);

    /**
     * 更新礼品剩余数量的方法
     * @param id 要改变数量的id
     */
    void updateInventory(Long id,Integer number);
}
