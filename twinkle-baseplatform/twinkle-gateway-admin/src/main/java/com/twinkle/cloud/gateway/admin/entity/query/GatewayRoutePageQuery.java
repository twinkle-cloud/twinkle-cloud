package com.twinkle.cloud.gateway.admin.entity.query;

import com.twinkle.cloud.common.mybatis.entity.query.BasePageQuery;
import com.twinkle.cloud.gateway.admin.entity.query.GatewayRouteQuery;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:24 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class GatewayRoutePageQuery extends BasePageQuery<GatewayRouteQuery> {
    @ApiModelProperty(value = "uri路径", required = true)
    private String uri;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询开始时间必须小于当前日期")
    @ApiModelProperty(value = "查询开始时间")
    private Date createdTimeStart;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "查询结束时间必须小于当前日期")
    @ApiModelProperty(value = "查询结束时间")
    private Date createdTimeEnd;
}
