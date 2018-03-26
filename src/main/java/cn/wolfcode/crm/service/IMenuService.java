package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.query.MenuQuery;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.util.PageResult;

import java.util.List;

/**
 * Demo class
 *
 * @author user
 * @date yyyy/MM/dd
 */
public interface IMenuService {

    int deleteByPrimaryKey(Long id);

    int insert(Menu entity);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu entity);

    List<Menu> getRootMenu();

    List<Menu> selectMenus(MenuQuery mq);

    void selectCheckMenu(List<Menu> menus);
}
