package com.twinkle.cloud.security.authorization.service.impl;

import com.twinkle.cloud.security.authorization.entity.User;
import com.twinkle.cloud.security.authorization.feign.OrganizationProvider;
import com.twinkle.cloud.security.authorization.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 10:40 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private OrganizationProvider organizationProvider;

    @Override
    public User getByUniqueId(String _id) {
        return this.organizationProvider.getUserByUniqueId(_id).getData();
    }
}
