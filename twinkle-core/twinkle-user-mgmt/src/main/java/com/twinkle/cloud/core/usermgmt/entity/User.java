package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.twinkle.cloud.common.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

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
@AllArgsConstructor
@TableName("U_USER")
public class User extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String loginName;
    private String password;
    private String wechatId;
    private String qqId;
    private String weiboId;
    private String phone;
    private Integer status = 1;

    @TableField(fill  = FieldFill.INSERT_UPDATE)
    private Date lastSigninDate;
    private Date nextRefreshDate;
    private Date expiredDate;
    @TableField(exist = false)
    private Set<String> roleIds;

    @TableField(exist = false)
    private Set<Integer> orgIds;

    @TableField(exist = false)
    private UserInfo userInfo;
}
