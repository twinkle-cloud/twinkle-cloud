package com.twinkle.cloud.common.data.authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/19/18 4:50 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class OrganizationItem {
    @ApiModelProperty(notes = "机构ID")
    private String id;

    @ApiModelProperty(notes = "组织机构/部门编码")
    private String code;

    @ApiModelProperty(notes = "组织机构/部门名字")
    private String name;

    @ApiModelProperty(notes = "组织机构/部门")
    private Byte status;

    @ApiModelProperty(notes = "机构负责人ID")
    private String leaderId;

    @ApiModelProperty(notes = "父节点ID")
    private String parentId;

    @ApiModelProperty(notes = "创建者ID")
    private String creatorId;

    @ApiModelProperty(notes = "租户ID")
    private String tenantId;

}
