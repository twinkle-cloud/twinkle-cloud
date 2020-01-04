package com.twinkle.cloud.core.usermgmt.entity.form;

import com.twinkle.cloud.common.mybatis.entity.query.BaseForm;
import com.twinkle.cloud.core.usermgmt.entity.Organization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 9:46 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class OrganizationForm extends BaseForm<Organization> {

    @NotBlank(message = "上级部门id不能为空")
    @ApiModelProperty(value = "上级部门id")
    private Integer parentId;

    @NotBlank(message = "部门名称不能为空")
    @ApiModelProperty(value = "用户组名称")
    private String name;

    @ApiModelProperty(value = "部门描述")
    private String comments;
}
