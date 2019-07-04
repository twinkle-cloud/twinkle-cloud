package com.twinkle.cloud.common.data.authority;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/19/18 4:45 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class RoleItem {
    @ApiModelProperty(notes = "角色ID")
    private String id;

    @ApiModelProperty(notes = "角色名")
    private String name;

    @ApiModelProperty(notes = "角色类型")
    private Byte type;

    private String code;

    @ApiModelProperty(notes = "状态")
    private Byte status;
}
