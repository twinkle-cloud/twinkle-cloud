package com.twinkle.cloud.common.data.authority;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/19/18 4:21 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class UserInfo {
    @ApiModelProperty(notes = "用户ID")
    private String id;

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty(notes = "登录账号")
    private String loginName;

    @ApiModelProperty(notes = "邮件")
    private String email;

    @ApiModelProperty(notes = "联系电话")
    private String phone;

    @ApiModelProperty(notes = "用户状态")
    private Byte status;

    @ApiModelProperty(notes = "用户类型")
    private Byte type;

    @ApiModelProperty(notes = "租户ID")
    private String tenantId;

    @ApiModelProperty(notes = "头像图片")
    private String logoPath;

    @ApiModelProperty(notes = "所在部门ID")
    private String orgId;

    @ApiModelProperty(notes = "描述")
    private String description;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp createDate;

    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private Timestamp updateDate;

}
