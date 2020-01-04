package com.twinkle.cloud.core.usermgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twinkle.cloud.core.usermgmt.entity.param.MenuQueryParam;
import com.twinkle.cloud.core.usermgmt.entity.Menu;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface MenuService {
    /**
     * 获取菜单
     *
     * @param _id
     * @return
     */
    Menu get(String _id);

    /**
     * 新增菜单
     *
     * @param _menu
     * @return
     */
    boolean add(Menu _menu);

    /**
     * 查询资源,分页
     *
     * @return
     */
    IPage<Menu> query(Page _page, MenuQueryParam _condition);

    /**
     * 查询菜单
     *
     * @return
     */
    List<Menu> query(MenuQueryParam _condition);

    /**
     * 根据username查询角色拥有的资源
     *
     * @return
     */
    List<Menu> query(String _userName);

    /**
     * 根据父id查询菜单
     *
     * @return
     */
    List<Menu> queryByParentId(String _id);

    /**
     * 更新菜单信息
     *
     * @param _menu
     */
    boolean update(Menu _menu);

    /**
     * 根据id删除菜单
     *
     * @param _id
     */
    boolean delete(String _id);
}
