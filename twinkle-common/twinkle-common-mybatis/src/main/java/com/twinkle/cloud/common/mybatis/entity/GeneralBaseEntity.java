package com.twinkle.cloud.common.mybatis.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/4/20 6:12 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class GeneralBaseEntity extends BaseEntity {
    @TableField(fill = FieldFill.INSERT)
    private String creatorId;
}
