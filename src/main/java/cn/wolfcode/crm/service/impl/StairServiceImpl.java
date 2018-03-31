package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Secondary;
import cn.wolfcode.crm.domain.Stair;
import cn.wolfcode.crm.mapper.SecondaryMapper;
import cn.wolfcode.crm.mapper.StairMapper;
import cn.wolfcode.crm.service.IStairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by China on 2018/3/26.
 */
@Service
public class StairServiceImpl implements IStairService {
    @Autowired
    private StairMapper stairMapper;
    @Autowired
    private SecondaryMapper secondaryMapper;

    @Override
    public void saveOrUpdate(Stair entity) {
        if (entity.getId() == null) {
            stairMapper.insert(entity);
        }else {
            stairMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {
        return stairMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Stair get(Long id) {
        return stairMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Stair> listAll() {
        return stairMapper.selectAll();
    }

    /**
     * 根据一级分类id查询二级商品分类!
     * @return
     */
    @Override
    public List<Secondary> getStairBySecondaryId(Long id) {
        List<Stair> databases = stairMapper.selectAll();
        List<Secondary> stairs = new ArrayList<>();

        for (Stair database : databases) {
            if(database.getSecondary_id().equals(id) ){
                stairs = secondaryMapper.selectByStairId(id);
            }
        }

        return stairs;
    }
}




















