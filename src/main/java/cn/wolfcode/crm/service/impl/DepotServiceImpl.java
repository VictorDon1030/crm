package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Depot;
import cn.wolfcode.crm.mapper.DepotMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepotService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/3/27 23:27
 */
@Service
public class DepotServiceImpl implements IDepotService {
    @Autowired
    private DepotMapper depotMapper;


    public int deleteByPrimaryKey(Long id) {
        return this.depotMapper.deleteByPrimaryKey(id);
    }

    public int save(Depot entity) {
        if (entity.getId() == null) {
            entity.setVdate(new Date());
            return this.depotMapper.insert(entity);
        } else {
            return this.depotMapper.updateByPrimaryKey(entity);
        }
    }

    public Depot selectByPrimaryKey(Long id) {
        return this.depotMapper.selectByPrimaryKey(id);
    }

    public List<Depot> selectAll() {
        return this.depotMapper.selectAll();
    }

    public PageResult query(QueryObject qo) {
        int total = this.depotMapper.queryCount(qo);
        List<Depot> data = this.depotMapper.queryForList(qo);
        return total == 0 ? new PageResult(total, Collections.EMPTY_LIST) : new PageResult(total, data);
    }

    public void changeState(Long id) {
        this.depotMapper.changeState(id);
    }

}
