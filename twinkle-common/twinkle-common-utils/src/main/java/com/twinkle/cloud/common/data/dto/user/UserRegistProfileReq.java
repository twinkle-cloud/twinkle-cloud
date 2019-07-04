package com.twinkle.cloud.common.data.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/22/18 12:02 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class UserRegistProfileReq {
    @ApiModelProperty(notes = "登录名")
    private String loginName;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("手机号验证码")
    private String verifyCode;

    @ApiModelProperty("租户Id")
    private String tenantId;
    @ApiModelProperty("租户名称")
    private String tenantName;
    @ApiModelProperty("租户编码")
    private String tenantCode;
}
