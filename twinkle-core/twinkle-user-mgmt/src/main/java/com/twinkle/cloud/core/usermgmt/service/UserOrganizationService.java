package com.twinkle.cloud.core.usermgmt.service;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 10:48 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface UserOrganizationService {
    /**
     * 给用户添加部门
     *
     * @param _userId
     * @param _orgIds
     * @return
     */
    boolean saveBatch(Long _userId, Set<Integer> _orgIds);

    /**
     * 删除用户拥有的某个部门
     *
     * @param _userId
     * @param _roleId
     * @return
     */
    boolean removeByUserId(Long _userId, Integer _roleId);

    /**
     * 删除用户拥有的部门
     *
     * @param _userId
     * @return
     */
    boolean removeByUserId(Long _userId);

    /**
     * 根据userId查询用户拥有部门id集合
     *
     * @param _userId
     * @return
     */
    Set<Integer> queryByUserId(Long _userId);
}
