package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.query.MenuQuery;
import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu entity);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu entity);

    List<Menu> getRootMenu();

    List<Menu> selectMenus(MenuQuery mq);

    void deleteByParentId(Long id);
}