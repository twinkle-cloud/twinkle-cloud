package com.twinkle.cloud.core.usermgmt.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 5:06 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class PasswordUpdateRequest implements Serializable {
    @ApiModelProperty(value = "用户ID")
    @NotBlank(message = "用户ID不能为空")
    private Long userId;
    /**
     * The original password.
     */
    @ApiModelProperty(value = "原密码")
    @NotBlank(message = "原密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;
    /**
     * The new password.
     */
    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String newPassword;
    /**
     * The new confirmed password.
     */
    @ApiModelProperty(value = "确认新密码")
    @NotBlank(message = "确认新密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String confirmedPassword;
}
