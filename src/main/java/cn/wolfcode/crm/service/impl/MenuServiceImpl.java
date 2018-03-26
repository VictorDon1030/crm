package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.mapper.MenuMapper;
import cn.wolfcode.crm.query.MenuQuery;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMenuService;
import cn.wolfcode.crm.util.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        menuMapper.deleteByParentId(id);
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Menu entity) {
        return menuMapper.insert(entity);
    }

    @Override
    public Menu selectByPrimaryKey(Long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectAll() {
        return menuMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Menu entity) {
        return menuMapper.updateByPrimaryKey(entity);
    }

    @Override
    public List<Menu> getRootMenu() {
        return menuMapper.getRootMenu();
    }

    @Override
    public List<Menu> selectMenus(MenuQuery mq) {
        return menuMapper.selectMenus(mq);
    }

    @Override
    public void selectCheckMenu(List<Menu> menus) {
        Subject subject = SecurityUtils.getSubject();
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()){
            Menu menu = iterator.next();
            if (menu.getPermission() != null) {
                if (!subject.isPermitted(menu.getPermission().getResource())) {
                    iterator.remove();
                    continue;
                }
            }
            if (menu.getChildren() != null) {
                selectCheckMenu(menu.getChildren());
            }
        }
    }
}
