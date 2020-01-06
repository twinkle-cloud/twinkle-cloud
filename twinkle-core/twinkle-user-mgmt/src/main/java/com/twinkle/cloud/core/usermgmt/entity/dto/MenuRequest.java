package com.twinkle.cloud.core.usermgmt.entity.dto;

import com.twinkle.cloud.common.mybatis.entity.dto.BaseRequest;
import com.twinkle.cloud.core.usermgmt.entity.Menu;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:50 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@ApiModel
@Data
public class MenuRequest extends BaseRequest<Menu> {
    @NotBlank(message = "菜单父id不能为空")
    @ApiModelProperty(value = "菜单父id")
    private String parentId;

    @NotBlank(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String name;

    @NotBlank(message = "菜单编码不能为空")
    @ApiModelProperty(value = "菜单编码")
    private String code;

    @NotBlank(message = "菜单类型不能为空")
    @ApiModelProperty(value = "菜单类型")
    private Integer type;

    @NotBlank(message = "菜单路径不能为空")
    @ApiModelProperty(value = "菜单路径")
    private String routing;

    @ApiModelProperty(value = "菜单图标")
    private String iPath;

    @ApiModelProperty(value = "菜单序号")
    private Integer seqNo;
}
