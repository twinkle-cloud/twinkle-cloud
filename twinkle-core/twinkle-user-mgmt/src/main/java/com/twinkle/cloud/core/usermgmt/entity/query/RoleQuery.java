package com.twinkle.cloud.core.usermgmt.entity.query;

import com.twinkle.cloud.common.mybatis.entity.query.BaseQuery;
import com.twinkle.cloud.core.usermgmt.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 9:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleQuery extends BaseQuery<Role> {
    private String code;
    private String name;
}
