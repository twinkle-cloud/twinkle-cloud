package com.twinkle.cloud.core.usermgmt.entity.param;

import com.twinkle.cloud.common.mybatis.entity.query.BaseParam;
import com.twinkle.cloud.core.usermgmt.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 9:50 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserQueryParam extends BaseParam<User> {
    private String loginName;
    private String phone;
    private String name;
}
