package com.twinkle.cloud.core.usermgmt.entity.vo;

import com.twinkle.cloud.common.mybatis.entity.vo.BaseVo;
import com.twinkle.cloud.core.usermgmt.entity.User;
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
public class UserInfoVo extends BaseVo<UserInfo> {
    public UserInfoVo(UserInfo _user) {
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
