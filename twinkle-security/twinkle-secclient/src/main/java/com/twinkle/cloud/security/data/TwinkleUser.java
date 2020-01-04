package com.twinkle.cloud.security.data;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 10:27 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class TwinkleUser extends User {

    /**
     * 用户ID
     */
    @Getter
    private Long id;
    /**
     * 部门ID
     */
    @Getter
    private Collection<Integer> orgIds;

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param _id
     * @param _orgIds
     * @param username
     * @param password
     * @param authorities
     */
    public TwinkleUser(Long _id, Collection<Integer> _orgIds, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = _id;
        this.orgIds = _orgIds;
    }

    /**
     * Construct the <code>User</code> with the details required by
     * {@link DaoAuthenticationProvider}.
     *
     * @param _id                    User's ID
     * @param _orgIds                User's Organizations.
     * @param username               the username presented to the
     *                               <code>DaoAuthenticationProvider</code>
     * @param password               the password that should be presented to the
     *                               <code>DaoAuthenticationProvider</code>
     * @param enabled                set to <code>true</code> if the user is enabled
     * @param accountNonExpired      set to <code>true</code> if the account has not expired
     * @param credentialsNonExpired  set to <code>true</code> if the credentials have not expired
     * @param accountNonLocked       set to <code>true</code> if the account is not locked
     * @param authorities            the authorities that should be granted to the caller if they
     *                               presented the correct username and password and the user is enabled. Not null.
     */
    public TwinkleUser(Long _id, Collection<Integer> _orgIds, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = _id;
        this.orgIds = _orgIds;
    }
}
