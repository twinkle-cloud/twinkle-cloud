package com.twinkle.cloud.security.authorization.feign;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.security.authorization.component.UserMgmtProviderFallback;
import com.twinkle.cloud.security.authorization.entity.Role;
import com.twinkle.cloud.security.authorization.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@FeignClient(name = "twinkle-usermgmt", fallback = UserMgmtProviderFallback.class)
public interface UserMgmtProvider {
    @GetMapping(value = "/user")
    GeneralResult<User> getUserByUniqueId(@RequestParam("uniqueId") String uniqueId);

    @GetMapping(value = "/role/user/{userId}")
    GeneralResult<Set<Role>> queryRolesByUserId(@PathVariable("userId") String userId);

}
