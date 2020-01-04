package com.twinkle.cloud.core.usermgmt.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.twinkle.cloud.core.usermgmt.entity.form.PasswordUpdateForm;
import com.twinkle.cloud.core.usermgmt.entity.param.UserQueryParam;
import com.twinkle.cloud.core.usermgmt.entity.User;
import com.twinkle.cloud.core.usermgmt.entity.vo.UserVo;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-27 16:45<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface UserService {
    /**
     * Get User info with given UserId.
     *
     * @param _id
     * @return
     */
    UserVo get(Long _id);

    /**
     * 根据用户唯一标识获取用户信息
     * 目前用户标识不用户名或mobile
     *
     * @param _id
     * @return
     */
    User getByUniqueId(String _id);

    /**
     * Add new User.
     *
     * @param user
     * @return
     */
    boolean add(User user);

    /**
     * Update the password for the user.
     *
     * @param _password
     * @return
     */
    boolean updatePassword(PasswordUpdateForm _password);

    /**
     * Reset the _userId's password.
     *
     * @param _userId
     * @return
     */
    boolean resetPassword(Long _userId);

    /**
     * Query the paged user list .
     *
     * @param _page
     * @param _queryParam
     * @return
     */
    IPage<UserVo> query(Page<User> _page, UserQueryParam _queryParam);

    /**
     * 更新用户信息
     *
     * @param _user
     */
    boolean update(User _user);

    /**
     * 根据id删除用户
     *
     * @param _id
     */
    boolean delete(Long _id);
}
