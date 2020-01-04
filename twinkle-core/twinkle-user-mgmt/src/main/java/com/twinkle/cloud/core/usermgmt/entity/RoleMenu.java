package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twinkle.cloud.common.mybatis.entity.GeneralBaseEntity;
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
@TableName("U_ROLE_MENU_MAP")
public class RoleMenu extends GeneralBaseEntity {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @NonNull
    private String roleId;
    @NonNull
    private String menuId;
    private Integer status = 1;
}
