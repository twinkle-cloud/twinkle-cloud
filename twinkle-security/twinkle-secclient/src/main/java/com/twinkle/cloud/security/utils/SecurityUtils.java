package com.twinkle.cloud.security.utils;

import cn.hutool.core.util.StrUtil;
import com.twinkle.cloud.common.constant.SecurityConstant;
import com.twinkle.cloud.security.data.TwinkleUser;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/3/20 6:21 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@UtilityClass
public class SecurityUtils {
    /**
     * 获取Authentication
     */
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户
     */
    public TwinkleUser getUser(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof TwinkleUser) {
            return (TwinkleUser) principal;
        }
        return null;
    }

    /**
     * 获取用户
     */
    public TwinkleUser getUser() {
        Authentication authentication = getAuthentication();
        if (authentication == null) {
            return null;
        }
        return getUser(authentication);
    }

    /**
     * 获取用户角色信息
     *
     * @return 角色集合
     */
    public List<Integer> getRoles() {
        Authentication authentication = getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<Integer> roleIds = new ArrayList<>();
        authorities.stream()
                .filter(granted -> StrUtil.startWith(granted.getAuthority(), SecurityConstant.ROLE))
                .forEach(granted -> {
                    String id = StrUtil.removePrefix(granted.getAuthority(), SecurityConstant.ROLE);
                    roleIds.add(Integer.parseInt(id));
                });
        return roleIds;
    }
}
