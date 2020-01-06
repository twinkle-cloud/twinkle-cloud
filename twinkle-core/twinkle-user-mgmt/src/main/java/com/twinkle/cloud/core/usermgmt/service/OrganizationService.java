package com.twinkle.cloud.core.usermgmt.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.twinkle.cloud.core.usermgmt.entity.query.OrganizationQuery;
import com.twinkle.cloud.core.usermgmt.entity.Organization;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 8:09 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface OrganizationService extends IService<Organization> {
    /**
     * 获取用户组
     *
     * @param _id
     * @return
     */
    Organization get(String _id);

    /**
     * 新增用户组
     *
     * @param _org
     * @return true为新增成功
     */
    boolean add(Organization _org);

    /**
     * 查询用户组
     * @param _condition
     * @return
     */
    List<Organization> query(OrganizationQuery _condition);

    /**
     * 根据父id查询用户组
     *
     * @param _parentId
     * @return
     */
    List<Organization> queryByParentId(String _parentId);

    /**
     * 更新用户组信息
     *
     * @param _org
     * @return
     */
    boolean update(Organization _org);

    /**
     * 根据id删除用户组
     *
     * @param _id
     * @return
     */
    boolean delete(String _id);
}
