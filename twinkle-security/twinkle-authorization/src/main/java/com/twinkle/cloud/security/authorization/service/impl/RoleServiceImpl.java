package com.twinkle.cloud.security.authorization.service.impl;

import com.twinkle.cloud.security.authorization.entity.Role;
import com.twinkle.cloud.security.authorization.feign.UserMgmtProvider;
import com.twinkle.cloud.security.authorization.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 10:39 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private UserMgmtProvider userMgmtProvider;

    @Override
    public Set<Role> queryUserRolesByUserId(String _userId) {
        return userMgmtProvider.queryRolesByUserId(_userId).getData();
    }
}
