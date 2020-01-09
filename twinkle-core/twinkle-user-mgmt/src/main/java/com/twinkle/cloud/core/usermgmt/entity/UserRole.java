package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twinkle.cloud.common.mybatis.entity.GeneralEntity;
import lombok.*;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2019-06-20 17:58<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@TableName("U_USER_ROLE_MAP")
public class UserRole extends GeneralEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * The User's ID
     */
    @NonNull
    private Long userId;
    /**
     * Role ID.
     */
    @NonNull
    private String roleId;
    /**
     * The status for the map relation.
     */
    private Integer status = 1;
}
