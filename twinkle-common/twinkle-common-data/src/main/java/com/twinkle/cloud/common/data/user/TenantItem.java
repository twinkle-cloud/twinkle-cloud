package com.twinkle.cloud.common.data.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class TenantItem {
    @ApiModelProperty(notes = "租户ID")
    private String id;
    @ApiModelProperty(notes = "租户名字")
    private String name;
    @ApiModelProperty(notes = "租户编码")
    private String code;
}
