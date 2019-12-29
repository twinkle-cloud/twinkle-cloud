package com.twinkle.cloud.gateway.admin.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.gateway.admin.entity.form.GatewayRouteForm;
import com.twinkle.cloud.gateway.admin.entity.form.GatewayRouteQueryForm;
import com.twinkle.cloud.gateway.admin.entity.ov.GatewayRouteVo;
import com.twinkle.cloud.gateway.admin.entity.param.GatewayRouteQueryParam;
import com.twinkle.cloud.gateway.admin.entity.po.TGatewayRoute;
import com.twinkle.cloud.gateway.admin.service.GatewayRouteService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/24/19 9:29 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/gateway/routes")
@Api("gateway routes")
@Slf4j
public class GatewayRouteController {
    @Autowired
    private GatewayRouteService gatewayRoutService;

    @ApiOperation(value = "新增网关路由", notes = "新增一个网关路由")
    @ApiImplicitParam(name = "gatewayRoutForm", value = "新增网关路由form表单", required = true, dataType = "GatewayRouteForm")
    @PostMapping
    public GeneralResult add(@Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        log.info("name:", gatewayRoutForm);
        TGatewayRoute gatewayRout = gatewayRoutForm.toPo(TGatewayRoute.class);
        return GeneralResult.success(gatewayRoutService.add(gatewayRout));
    }

    @ApiOperation(value = "删除网关路由", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @DeleteMapping(value = "/{id}")
    public GeneralResult delete(@PathVariable String id) {
        return GeneralResult.success(gatewayRoutService.delete(id));
    }

    @ApiOperation(value = "修改网关路由", notes = "修改指定网关路由信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "网关路由ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "gatewayRoutForm", value = "网关路由实体", required = true, dataType = "GatewayRouteForm")
    })
    @PutMapping(value = "/{id}")
    public GeneralResult update(@PathVariable String id, @Valid @RequestBody GatewayRouteForm gatewayRoutForm) {
        TGatewayRoute gatewayRout = gatewayRoutForm.toPo(TGatewayRoute.class);
        gatewayRout.setId(id);
        return GeneralResult.success(gatewayRoutService.update(gatewayRout));
    }

    @ApiOperation(value = "获取网关路由", notes = "根据id获取指定网关路由信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "网关路由ID", required = true, dataType = "long")
    @GetMapping(value = "/{id}")
    public GeneralResult get(@PathVariable String id) {
        log.info("get with id:{}", id);
        return GeneralResult.success(new GatewayRouteVo(gatewayRoutService.get(id)));
    }

    @ApiOperation(value = "根据uri获取网关路由", notes = "根据uri获取网关路由信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "name", value = "网关路由路径", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class)
    )
    @GetMapping
    public GeneralResult getByUri(@RequestParam String uri) {
        return GeneralResult.success(gatewayRoutService.query(new GatewayRouteQueryParam(uri)).stream().findFirst());
    }

    @ApiOperation(value = "搜索网关路由", notes = "根据条件查询网关路由信息")
    @ApiImplicitParam(name = "gatewayRoutQueryForm", value = "网关路由查询参数", required = true, dataType = "GatewayRouteQueryForm")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class)
    )
    @PostMapping(value = "/conditions")
    public GeneralResult search(@Valid @RequestBody GatewayRouteQueryForm gatewayRouteQueryForm) {
        return GeneralResult.success(gatewayRoutService.query(gatewayRouteQueryForm.toParam(GatewayRouteQueryParam.class)));
    }

    @ApiOperation(value = "重载网关路由", notes = "将所以网关的路由全部重载到redis中")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class)
    )
    @PostMapping(value = "/overload")
    public GeneralResult overload() {
        return GeneralResult.success(gatewayRoutService.overload());
    }

}
