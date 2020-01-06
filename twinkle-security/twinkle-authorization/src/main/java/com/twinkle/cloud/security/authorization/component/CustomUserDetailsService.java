package com.twinkle.cloud.security.authorization.component;

import com.twinkle.cloud.common.constant.ResultCode;
import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.common.data.usermgmt.SecurityRole;
import com.twinkle.cloud.common.data.usermgmt.SecurityUser;
import com.twinkle.cloud.security.authorization.feign.UserMgmtProvider;
import com.twinkle.cloud.security.data.TwinkleUser;
import com.twinkle.cloud.security.exception.UnauthorizedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:22 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Slf4j
@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMgmtProvider userMgmtProvider;

    @Override
    public UserDetails loadUserByUsername(String _uniqueId) {
        GeneralResult<SecurityUser> tempResult = this.userMgmtProvider.getUserByUniqueId(_uniqueId);
        if (tempResult.getCode().equals(ResultCode.OPERATION_SUCCESS)) {
            SecurityUser tempUser = tempResult.getData();
            log.info("Load user by username :{}", tempUser);

            TwinkleUser tempTwinkleUser = new TwinkleUser(
                    tempUser.getId(),
                    tempUser.getTenantId(),
                    tempUser.getManagedOrgIds(),
                    tempUser.getLoginName(),
                    tempUser.getPassword(),
                    this.obtainGrantedAuthorities(tempUser)
            );
            return tempTwinkleUser;
        }
        throw new UnauthorizedException("Did not find user [" + _uniqueId + "].");
    }

    /**
     * 获得登录者所有角色的权限集合.
     *
     * @param user
     * @return
     */
    protected Set<GrantedAuthority> obtainGrantedAuthorities(SecurityUser user) {
        GeneralResult<Set<SecurityRole>> tempResult = this.userMgmtProvider.queryRolesByUserId((Long) user.getId());

        if (tempResult.getCode().equals(ResultCode.OPERATION_SUCCESS)) {
            log.info("User:{}, Roles:{}", user.getLoginName(), tempResult.getData());
            return tempResult.getData().stream().map(item -> new SimpleGrantedAuthority(item.getCode())).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }
}
