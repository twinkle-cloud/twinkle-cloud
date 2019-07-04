package com.twinkle.cloud.common.data.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     11/22/18 11:53 AM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@Data
@ApiModel
public class UserProfileReq {
    @ApiModelProperty(notes = "ID")
    private String id;

    @ApiModelProperty(notes = "登录名")
    private String loginName;

    @ApiModelProperty(notes = "密码")
    private String password;

    @ApiModelProperty(notes = "用户名")
    private String userName;

    @ApiModelProperty("状态")
    private Byte status;

    @ApiModelProperty(notes = "用户类型")
    private Byte type;

    @ApiModelProperty(notes = "用户邮箱")
    private String email;

    @ApiModelProperty(notes = "用户手机号")
    private String phone;

    @ApiModelProperty(notes = "景栗平台id")
    private String uuid;

    @ApiModelProperty(notes = "用户微信号")
    private String wechat;

    @ApiModelProperty(notes = "用户qq号")
    private String qq;

    @ApiModelProperty(notes = "用户性别")
    private Byte gender;

    @ApiModelProperty(notes = "用户年龄")
    private Integer age;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    @ApiModelProperty(notes = "用户生日")
    private Date birthday;

    @ApiModelProperty(notes = "所属租户ID")
    private String tenantId;

    @ApiModelProperty(notes = "用户头像")
    private String logoPath;


    @ApiModelProperty(notes = "会员等级")
    private String level;

}
