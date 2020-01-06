package com.twinkle.cloud.core.usermgmt.entity.query;

import com.twinkle.cloud.common.mybatis.entity.query.BasePageQuery;
import com.twinkle.cloud.core.usermgmt.entity.query.RoleQuery;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 9:34 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
public class RolePageQuery extends BasePageQuery<RoleQuery> {
    @ApiModelProperty(value = "角色编码")
    private String code;

    @ApiModelProperty(value = "角色名称")
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
