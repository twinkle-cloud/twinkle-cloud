package com.twinkle.cloud.security.authorization.feign;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.data.usermgmt.SecurityRole;
import com.twinkle.cloud.common.data.usermgmt.SecurityUser;
import com.twinkle.cloud.security.authorization.component.UserMgmtProviderFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:26 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@FeignClient(name = "twinkle-user-mgmt", fallback = UserMgmtProviderFallback.class)
public interface UserMgmtProvider {
    /**
     * Try to load the user info with given login name.
     *
     * @param _uniqueId
     * @return
     */
    @GetMapping(value = "/noauth/user")
    GeneralResult<SecurityUser> getUserByUniqueId(@RequestParam("_uniqueId") String _uniqueId);

    /**
     * To get the roles granted to the given user.
     *
     * @param _userId
     * @return
     */
    @GetMapping(value = "/noauth/roles")
    GeneralResult<Set<SecurityRole>> queryRolesByUserId(@RequestParam("_userId") Long _userId);
}
