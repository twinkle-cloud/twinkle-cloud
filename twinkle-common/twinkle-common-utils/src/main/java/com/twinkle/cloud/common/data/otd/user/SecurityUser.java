package com.twinkle.cloud.common.data.otd.user;

import com.twinkle.cloud.common.data.authority.Permission;
import lombok.Data;

import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/15/18 11:11 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class SecurityUser {
    public String id;
    public String username;
    public String name;
    private String description;
    private String image;
    private List<Permission> menus;
    private List<Permission> elements;
}
