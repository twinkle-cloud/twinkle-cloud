package com.twinkle.cloud.common.data.dto.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/22/18 11:54 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class UserPasswordReq {
    private String userId;
    private String oldPassword;
    private String newPassword;
    private String newConfirmPassword;
}
