package com.twinkle.cloud.common.mybatis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/21/19 3:33 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
public class BaseEntity implements Serializable {
    public final static String DEFAULT_USERNAME = "system";

    @TableId(type = IdType.ASSIGN_UUID)
    private Serializable id;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Date createDate;
//
//    @TableField(fill = FieldFill.INSERT_UPDATE)
//    private String updatedBy;

    @TableField(insertStrategy = FieldStrategy.NEVER, updateStrategy = FieldStrategy.NEVER)
    private Date updateDate;
}
