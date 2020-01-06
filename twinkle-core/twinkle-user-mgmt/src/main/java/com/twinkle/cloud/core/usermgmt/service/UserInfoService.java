package com.twinkle.cloud.core.usermgmt.service;

import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import com.twinkle.cloud.core.usermgmt.entity.otd.UserInfoResponse;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/4/20 11:54 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface UserInfoService {
    /**
     * Query the user's profile with given user id.
     *
     * @param _userId
     * @return
     */
    UserInfo getByUserId(Long _userId);

    /**
     * Get the UserInfoResponse with given user id.
     *
     * @param _userId
     * @return
     */
    UserInfoResponse getUserInfoResponseByUserId(Long _userId);

    /**
     * Add profile for some user.
     *
     * @param _profile
     * @return
     */
    boolean add(UserInfo _profile);

    /**
     * Update User info with UserId.
     *
     * @param _profile
     * @param _userId
     * @return
     */
    boolean updateByUserId(UserInfo _profile, Long _userId);

    /**
     * Delete the user profile with given user Id.
     *
     * @param _id
     */
    boolean deleteByUserId(Long _userId);
}
