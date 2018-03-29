package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Linkage;
import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.LinkageMapper;
import cn.wolfcode.crm.service.ILinkageService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by China on 2018/3/22.
 */
@Service
public class LinkageServiceImpl implements ILinkageService {
    @Autowired
    private LinkageMapper linkageMapper;

    @Override
    public void saveOrUpdate(Linkage entity) {
        if (entity.getId() == null) {
            linkageMapper.insert(entity);
        }else {
            linkageMapper.updateByPrimaryKey(entity);
        }
    }

    @Override
    public int delete(Long id) {

        return linkageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Linkage get(Long id) {

        return linkageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Linkage> list() {

        return linkageMapper.selectAll();
    }

    @Override
    public List<Linkage> getRootLinkage() {
        return linkageMapper.getRootLinkage();
    }

    //检查菜单权限 没有权限就不显示他该有的菜单
    @Override
    public void selectPermission(List<Linkage> linkages) {
        //获取迭代器
        Iterator<Linkage> iterator = linkages.iterator();
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //1遍历菜单集合 取出每一个菜单
        while (iterator.hasNext()){
            Linkage linkage = iterator.next();
            //2 判断该菜单是否关联着权限 如果关联了 就需要进行权限判断 否则就直接放行
            Permission permission = linkage.getPermission();
            if (permission != null){
                //3 如果没有权限就从集合中移除掉
                if (!subject.isPermitted(permission.getResource())){
                    iterator.remove();
                    continue;
                }
            }
            //递归判断子菜单
            if (linkage.getChildren().size() > 0){
                this.selectPermission(linkage.getChildren());
            }

        }
    }
}






















