package com.twinkle.cloud.security.authorization.component;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authorization.entity.Role;
import com.twinkle.cloud.security.authorization.entity.User;
import com.twinkle.cloud.security.authorization.feign.UserMgmtProvider;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:27 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Component
public class UserMgmtProviderFallback implements UserMgmtProvider {
    @Override
    public GeneralResult<User> getUserByUniqueId(String uniqueId) {
        return GeneralResult.success(new User());
    }

    @Override
    public GeneralResult<Set<Role>> queryRolesByUserId(Long userId) {
        return GeneralResult.success(new HashSet<Role>());
    }

}
