package com.twinkle.cloud.security.authorization.entity;

import com.twinkle.cloud.common.mybatis.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 6:20 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@EqualsAndHashCode(callSuper = false )
@NoArgsConstructor
public class Role extends BaseEntity {
    private String code;
    private String name;
    private String description;
}
