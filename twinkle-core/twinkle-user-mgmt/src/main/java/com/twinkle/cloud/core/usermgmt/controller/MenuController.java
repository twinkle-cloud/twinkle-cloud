package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.core.usermgmt.entity.dto.MenuRequest;
import com.twinkle.cloud.core.usermgmt.entity.query.MenuPageQuery;
import com.twinkle.cloud.core.usermgmt.entity.query.MenuQuery;
import com.twinkle.cloud.core.usermgmt.entity.Menu;
import com.twinkle.cloud.core.usermgmt.service.MenuService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     1/1/20 10:32 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Api("Menu Management")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "新增菜单", notes = "新增一个菜单")
    @ApiImplicitParam(name = "menuForm", value = "新增菜单form表单", required = true, dataType = "MenuForm")
    @PostMapping(value = "/authsec/menu")
    public GeneralResult add(@Valid @RequestBody MenuRequest _menu) {
        log.debug("name:{}", _menu);
        Menu tempMenu = _menu.toPo(Menu.class);
        return GeneralResult.success(this.menuService.add(tempMenu));
    }

    @ApiOperation(value = "删除菜单", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "菜单ID", required = true, dataType = "long")
    @DeleteMapping(value = "/authsec/menu/{_id}")
    public GeneralResult delete(@PathVariable String _id) {
        return GeneralResult.success(this.menuService.delete(_id));
    }

    @ApiOperation(value = "修改菜单", notes = "修改指定菜单信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "_id", value = "菜单ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "_menu", value = "菜单实体", required = true, dataType = "MenuForm")
    })
    @PutMapping(value = "/authsec/menu/{_id}")
    public GeneralResult update(@PathVariable String _id, @Valid @RequestBody MenuRequest _menu) {
        Menu tempMenu = _menu.toPo(Menu.class);
        tempMenu.setId(_id);
        return GeneralResult.success(this.menuService.update(tempMenu));
    }

    @ApiOperation(value = "获取菜单", notes = "获取指定菜单信息")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "菜单ID", required = true, dataType = "long")
    @GetMapping(value = "/authsec/menu/{_id}")
    public GeneralResult get(@PathVariable String _id) {
        log.debug("get with id:{}", _id);
        return GeneralResult.success(this.menuService.get(_id));
    }

    @ApiOperation(value = "查询菜单", notes = "根据条件查询菜单信息，简单查询")
    @ApiImplicitParam(paramType = "query", name = "_name", value = "菜单名称", required = true, dataType = "string")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @GetMapping(value = "/authsec/menu")
    public GeneralResult query(@RequestParam(value = "_name", required = false) String _name) {
        log.debug("Query with name:{}", _name);
        MenuQuery tempMenuParam = new MenuQuery();
        tempMenuParam.setName(_name);
        return GeneralResult.success(this.menuService.query(tempMenuParam));
    }

    @ApiOperation(value = "搜索菜单", notes = "根据条件查询菜单信息")
    @ApiImplicitParam(name = "menuQueryForm", value = "菜单查询参数", required = true, dataType = "MenuQueryForm")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @PostMapping(value = "/authsec/menu/conditions")
    public GeneralResult search(@Valid @RequestBody MenuPageQuery _condition) {
        log.debug("search with menuQueryForm:{}", _condition);
        return GeneralResult.success(this.menuService.query(_condition.toParam(MenuQuery.class)));
    }

    @ApiOperation(value = "根据父id查询菜单", notes = "根据父id查询菜单列表")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "菜单父ID", required = true, dataType = "long")
    @GetMapping(value = "/authsec/menu/parent/{_id}")
    public GeneralResult search(@PathVariable String _id) {
        log.debug("query with parent id:{}", _id);
        return GeneralResult.success(this.menuService.queryByParentId(_id));
    }

    @ApiOperation(value = "根据登录名查询菜单", notes = "根据userId查询用户所拥有的资源信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "用户id", required = true, dataType = "long")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @GetMapping(value = "/authsec/menu/user/{_userName}")
    public GeneralResult queryByUsername(@PathVariable String _userName) {
        log.debug("query with username:{}", _userName);
        return GeneralResult.success(this.menuService.query(_userName));
    }
}
