package com.twinkle.cloud.core.usermgmt.service;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 10:25 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface UserRoleService {
    /**
     * 给用户添加角色
     *
     * @param _userId
     * @param _roleIds
     * @return
     */
    boolean saveBatch(Long _userId, Set<String> _roleIds);

    /**
     * 删除用户拥有的角色
     *
     * @param _userId
     * @return
     */
    boolean removeByUserId(Long _userId);

    /**
     * 根据userId查询用户拥有角色id集合
     *
     * @param _userId
     * @return
     */
    Set<String> queryByUserId(Long _userId);
}
