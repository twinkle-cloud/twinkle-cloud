package com.twinkle.cloud.common.data.authority;

import com.twinkle.cloud.common.data.user.TenantItem;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/19/18 4:42 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public final class UserDetailsItem extends UserInfo implements Principal {

    @Override
    public String getName() {
        return this.getLoginName();
    }
    @ApiModelProperty("所拥有的角色")
    private List<RoleItem> roles;
    @ApiModelProperty("所管理的部门/机构")
    private List<OrganizationItem> organizations;
    @ApiModelProperty("所属租户信息")
    private TenantItem tenantInfo;

    public void addRole(RoleItem _role) {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        this.roles.add(_role);
    }
    public void removeRole(RoleItem _role){
        this.roles.remove(_role);
    }

    public void addOrganization(OrganizationItem _organization){
        if(this.organizations == null) {
            this.organizations = new ArrayList<>();
        }
        this.organizations.add(_organization);
    }
    public void removeOrganization(OrganizationItem _organization){
        this.organizations.remove(_organization);
    }
}
