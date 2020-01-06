package com.twinkle.cloud.core.usermgmt.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.twinkle.cloud.common.mybatis.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/30/19 5:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("U_USER_INFO")
public class UserInfo extends BaseEntity {
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * The name for the user.
     */
    private String name;
    /**
     * The gender: 0 female, 1 male.
     */
    private Integer gender;
    /**
     * Title.
     */
    private Integer title;
    /**
     * Mail Address.
     */
    private String eMail;

    /**
     * Org ID which belongs to.
     */
    private Integer orgId;
    /**
     * The status.
     */
    private Integer status = 1;
    /**
     * Security Question.
     */
    private String secQuestion;
    /**
     * Security answer for the question.
     */
    private String secAnswer;
    /**
     * The tenant of this user.
     */
    private String tenantId;
    /**
     * USER.ID
     */
    private Long userId;
}
