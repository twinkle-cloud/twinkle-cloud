package com.twinkle.cloud.core.usermgmt.entity.form;

import com.twinkle.cloud.common.mybatis.entity.query.BaseForm;
import com.twinkle.cloud.core.usermgmt.entity.User;
import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import com.twinkle.cloud.core.usermgmt.entity.vo.UserInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 4:47 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class UserForm extends BaseForm<User> {
    @ApiModelProperty(value = "用户账号")
    @NotBlank(message = "用户名不能为空")
    @Length(min = 3, max = 20, message = "用户名长度在3到20个字符")
    private String loginName;

    @ApiModelProperty(value = "用户密码")
    @NotBlank(message = "用户密码不能为空")
    @Length(min = 5, max = 20, message = "密码长度在5到20个字符")
    private String password;

    @ApiModelProperty(value = "用户手机号")
    @NotBlank(message = "用户手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "用户姓名")
    @NotBlank(message = "用户姓名不能为空")
    private String name;

    @ApiModelProperty(value = "用户Email")
    private String email;

    @ApiModelProperty(value = "用户性别")
    private Integer gender = 1;

    @ApiModelProperty(value = "用户职位")
    private Integer title = 1;

    @ApiModelProperty(value = "用户拥有的角色id列表")
    private Set<String> roleIds;

    @ApiModelProperty(value = "用户所在部门id列表")
    private Set<Integer> orgIds;

    /**
     * The tenant of this user.
     */
    @ApiModelProperty(value = "用户所属租户")
    private String tenantId;

    public void addProfile(UserInfoVo _profile) {
        this.name = _profile.getName();
        this.email = _profile.getEMail();
        this.gender = _profile.getGender();
        this.title = _profile.getTitle();
        this.tenantId = _profile.getTenantId();
    }

    public UserInfo parseUserInfo() {
        UserInfo tempUserInfo = new UserInfo();
        tempUserInfo.setEMail(this.email);
        tempUserInfo.setGender(this.gender);
        tempUserInfo.setName(this.name);
        tempUserInfo.setTitle(this.title);
        tempUserInfo.setTenantId(this.tenantId);
        return tempUserInfo;
    }
}
