package com.twinkle.cloud.core.usermgmt.service;

import com.twinkle.cloud.core.usermgmt.entity.RoleMenu;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:02 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface RoleMenuService {
    /**
     * 给Role添加菜单
     *
     * @param _roleId
     * @param _menuIds
     * @return
     */
    boolean saveBatch(Serializable _roleId, Set<String> _menuIds);

    /**
     * 删除Role拥有的菜单
     *
     * @param _roleId
     * @return
     */
    boolean removeByRoleId(Serializable _roleId);

    /**
     * 根据userId查询用户拥有菜单id集合
     *
     * @param _roleId
     * @return
     */
    Set<String> queryByRoleId(String _roleId);

    /**
     * Query role menu items with given role ids.
     *
     * @param _roleIds
     * @return
     */
    List<RoleMenu> queryByRoleIds(Set<String> _roleIds);
}
