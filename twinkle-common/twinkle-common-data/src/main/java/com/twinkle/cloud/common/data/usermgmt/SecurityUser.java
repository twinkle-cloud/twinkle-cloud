package com.twinkle.cloud.common.data.usermgmt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/22/18 3:44 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class SecurityUser implements Serializable {
    /**
     * User's ID.
     */
    @ApiModelProperty(notes = "用户ID")
    private Long id;
    /**
     * User' Name.
     */
    @ApiModelProperty(notes = "用户名")
    private String name;
    /**
     * Phone No.
     */
    @ApiModelProperty(notes = "用户手机号")
    private String phone;
    /**
     * Login Name.
     */
    @ApiModelProperty(notes = "登录名")
    private String loginName;
    /**
     * Password.
     */
    @ApiModelProperty(notes = "登录密码")
    private String password;
    /**
     * Tenant ID.
     */
    @ApiModelProperty(notes = "所属租户ID")
    private String tenantId;
    /**
     * 用户所拥有角色IDs
     */
    @ApiModelProperty(notes = "所拥有角色IDs")
    private Set<String> roleIds;
    /**
     * 用户所属部门ID
     */
    @ApiModelProperty(notes = "所属部门ID")
    private Integer orgId;
    /**
     * 用户所管理部门IDs
     */
    @ApiModelProperty(notes = "所管理部门IDs")
    private Set<Integer> managedOrgIds;
}
