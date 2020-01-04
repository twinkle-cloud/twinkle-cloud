package com.twinkle.cloud.core.usermgmt.entity.param;

import com.twinkle.cloud.common.mybatis.entity.query.BaseParam;
import com.twinkle.cloud.core.usermgmt.entity.Organization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 8:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationQueryParam extends BaseParam<Organization> {
    private String code;
    private String name;
    private Integer status;
}
