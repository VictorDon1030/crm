package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Linkage;

import java.util.List;

/**
 * Created by China on 2018/3/22.
 */
public interface ILinkageService {

    void saveOrUpdate(Linkage entity);

    int delete(Long id);

    Linkage get(Long id);

    List<Linkage> list();

    List<Linkage> getRootLinkage();

    //检查菜单权限
    void selectPermission(List<Linkage> linkages);

}
