package com.twinkle.cloud.security.authorization.component;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.data.usermgmt.SecurityRole;
import com.twinkle.cloud.common.data.usermgmt.SecurityUser;
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
    public GeneralResult<SecurityUser> getUserByUniqueId(String _uniqueId) {
        return GeneralResult.success(new SecurityUser());
    }

    @Override
    public GeneralResult<Set<SecurityRole>> queryRolesByUserId(Long _userId) {
        return GeneralResult.success(new HashSet<SecurityRole>());
    }
}
