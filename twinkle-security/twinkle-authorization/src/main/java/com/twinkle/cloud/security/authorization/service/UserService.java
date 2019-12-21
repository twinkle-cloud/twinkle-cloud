package com.twinkle.cloud.security.authorization.service;

import com.twinkle.cloud.security.authorization.entity.User;
import org.springframework.cache.annotation.Cacheable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:23 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public interface UserService {
    /**
     * 根据用户唯一标识获取用户信息
     *
     * @param _id
     * @return
     */
    @Cacheable(value = "#id")
    User getByUniqueId(String _id);
}
