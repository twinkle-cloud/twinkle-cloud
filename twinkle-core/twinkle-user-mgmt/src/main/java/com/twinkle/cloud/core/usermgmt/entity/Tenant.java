package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.twinkle.cloud.common.mybatis.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 5:52 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("U_USER_ROLE_MAP")
public class Tenant extends AbstractEntity {
    /**
     * The ID for the tenant.
     */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    /**
     * Code for the tenant.
     */
    private String code;
    /**
     * Name for the tenant.
     */
    private String name;
    /**
     * The logo for the tenant.
     */
    private String logo;
    /**
     * The type for the tenant.
     */
    private Integer type;
    /**
     * The industry for the tenant.
     */
    private Integer industry;
    /**
     * The status for the tenant.
     */
    private Integer status;
}
