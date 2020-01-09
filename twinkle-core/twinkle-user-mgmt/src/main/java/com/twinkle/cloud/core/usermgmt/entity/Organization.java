package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twinkle.cloud.common.mybatis.entity.GeneralEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 5:38 PM<br/>
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
public class Organization extends GeneralEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * Code for the organization.
     */
    private String code;
    /**
     * Name for the organization.
     */
    private String name;
    /**
     * Organization level.
     */
    private Integer level;
    /**
     * The parent organization's ID
     */
    private Integer parentId;
    /**
     * The status for the organization.
     */
    private Integer status;
    /**
     * The comments.
     */
    private String comments;
    /**
     * The Tenant ID.
     */
    private String tenantId;
}
