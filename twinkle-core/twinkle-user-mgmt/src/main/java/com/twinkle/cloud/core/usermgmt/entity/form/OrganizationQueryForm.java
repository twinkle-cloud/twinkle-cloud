package com.twinkle.cloud.core.usermgmt.entity.form;

import com.twinkle.cloud.common.mybatis.entity.query.BaseQueryForm;
import com.twinkle.cloud.core.usermgmt.entity.param.OrganizationQueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/2/20 9:11 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class OrganizationQueryForm extends BaseQueryForm<OrganizationQueryParam> {
    @NotBlank(message = "组织机构名称不能为空")
    @ApiModelProperty(value = "用户组名称", required = true)
    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createDateStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createDateEnd;
}
