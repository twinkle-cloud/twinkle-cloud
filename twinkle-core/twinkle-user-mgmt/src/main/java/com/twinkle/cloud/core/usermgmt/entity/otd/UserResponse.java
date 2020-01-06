package com.twinkle.cloud.core.usermgmt.entity.otd;

import com.twinkle.cloud.common.mybatis.entity.otd.BaseResponse;
import com.twinkle.cloud.core.usermgmt.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 9:58 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@NoArgsConstructor
public class UserResponse extends BaseResponse<User> {
    public UserResponse(User _user) {
        BeanUtils.copyProperties(_user, this);
    }

    private String name;
    private String phone;
    private String wechatId;
    private String qqId;
    private String weiboId;
    private Integer status = 1;

    private String deleted;
    private Set<String> roleIds;

    private UserInfoResponse userInfo;

    private String creatorId;
    private Date createDate;
    private Date updateDate;
}
