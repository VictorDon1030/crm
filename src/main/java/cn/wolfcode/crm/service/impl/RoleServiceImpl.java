package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        roleMapper.deleteRealtion(id);
        return roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Role entity,Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                roleMapper.insertRelation(entity.getId(),id);
            }
        }
        return roleMapper.insert(entity);
    }

    @Override
    public Role selectByPrimaryKey(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Role entity,Long[] ids) {
        roleMapper.deleteRealtion(entity.getId());
        if (ids != null) {
            for (Long id : ids) {
                roleMapper.insertRelation(entity.getId(),id);
            }
        }
        return roleMapper.updateByPrimaryKey(entity);
    }

    @Override
    public PageResult query(QueryObject qo) {
        int total = roleMapper.queryCount(qo);
        List<Role> data = roleMapper.queryForList(qo);
        if (total == 0){
            return new PageResult(total, Collections.EMPTY_LIST);
        }
        return new PageResult(total, data);
    }



    @Override
    public List<Long> selectRoleIdByEmpId(Long id) {
        return roleMapper.selectRoleIdByEmpId(id);
    }

}
