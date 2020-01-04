package com.twinkle.cloud.core.usermgmt.entity.form;

import com.twinkle.cloud.common.mybatis.entity.query.BaseForm;
import com.twinkle.cloud.core.usermgmt.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 6:42 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class RoleForm extends BaseForm<Role> {
    @NotBlank(message = "角色编码不能为空")
    @ApiModelProperty(value = "角色编码")
    private String code;

    @NotBlank(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色描述")
    private String comments;

    @ApiModelProperty(value = "角色拥有的资源id列表")
    private Set<String> menuIds;
}
