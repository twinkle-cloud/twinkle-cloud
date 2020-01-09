package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twinkle.cloud.common.mybatis.entity.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 5:36 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("U_USER_ORG_MAP")
public class UserOrg extends AbstractEntity {
    /**
     * The User's ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * The User's ID
     */
    private Long userId;
    /**
     * Role ID.
     */
    private Integer orgId;
    /**
     * The status for the map relation.
     */
    private Integer status = 1;

    public UserOrg (Long _userId, Integer _orgId) {
        this.userId = _userId;
        this.orgId = _orgId;
        this.status = 1;
    }
}
