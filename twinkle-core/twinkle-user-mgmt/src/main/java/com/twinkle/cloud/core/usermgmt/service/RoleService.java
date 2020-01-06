package com.twinkle.cloud.core.usermgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twinkle.cloud.common.data.usermgmt.SecurityRole;
import com.twinkle.cloud.core.usermgmt.entity.query.RoleQuery;
import com.twinkle.cloud.core.usermgmt.entity.Role;

import java.util.List;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 6:41 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface RoleService {
    /**
     * 获取角色
     *
     * @param id
     * @return
     */
    Role get(String id);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Role> getAll();

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    boolean add(Role role);

    /**
     * 查询角色
     *
     * @return
     */
    IPage<Role> queryByUserId(Page page, RoleQuery roleQueryParam);

    /**
     * 根据用户id查询用户拥有的角色
     *
     * @return
     * @param _userId
     */
    Set<SecurityRole> queryByUserId(Long _userId);

    /**
     * 更新角色信息
     *
     * @param role
     */
    boolean update(Role role);

    /**
     * 根据id删除角色
     *
     * @param id
     */
    boolean delete(String id);
}
