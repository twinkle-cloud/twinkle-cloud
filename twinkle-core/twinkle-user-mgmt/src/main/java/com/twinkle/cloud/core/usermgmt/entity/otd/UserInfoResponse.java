package com.twinkle.cloud.core.usermgmt.entity.otd;

import com.twinkle.cloud.common.mybatis.entity.otd.BaseResponse;
import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 10:00 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class UserInfoResponse extends BaseResponse<UserInfo> {
    public UserInfoResponse(UserInfo _user) {
        BeanUtils.copyProperties(_user, this);
    }
    /**
     * The name for the user.
     */
    private String name;
    /**
     * The gender: 0 female, 1 male.
     */
    private Integer gender;
    /**
     * Title.
     */
    private Integer title;
    /**
     * Mail Address.
     */
    private String eMail;
    /**
     * The Org which the user belongs to.
     */
    private Integer orgId;
    /**
     * The status.
     */
    private Integer status;
    /**
     * Security Question.
     */
    private String secQuestion;
    /**
     * Security answer for the question.
     */
    private String secAnswer;
    /**
     * The tenant of this user.
     */
    private String tenantId;

    private Long userId;
}
