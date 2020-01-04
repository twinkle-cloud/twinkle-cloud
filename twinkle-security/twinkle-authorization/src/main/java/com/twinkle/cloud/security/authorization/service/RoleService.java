package com.twinkle.cloud.security.authorization.service;

import com.twinkle.cloud.security.authorization.entity.Role;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:24 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface RoleService {
    /**
     * Get the Role list with given _userId.
     *
     * @param _userId
     * @return
     */
    Set<Role> queryUserRolesByUserId(Long _userId);
}
