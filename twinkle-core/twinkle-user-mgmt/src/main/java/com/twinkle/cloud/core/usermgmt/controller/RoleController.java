package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.core.usermgmt.entity.form.RoleForm;
import com.twinkle.cloud.core.usermgmt.entity.form.RoleQueryForm;
import com.twinkle.cloud.core.usermgmt.entity.param.RoleQueryParam;
import com.twinkle.cloud.core.usermgmt.entity.Role;
import com.twinkle.cloud.core.usermgmt.service.RoleService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 6:38 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Api("Role Management")
@Slf4j
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "新增角色", notes = "新增一个角色")
    @ApiImplicitParam(name = "roleForm", value = "新增角色form表单", required = true, dataType = "RoleForm")
    @PostMapping(value = "/authsec/role")
    public GeneralResult add(@Valid @RequestBody RoleForm _role) {
        log.debug("name:{}", _role);
        Role tempRole = _role.toPo(Role.class);
        return GeneralResult.success(this.roleService.add(tempRole));
    }

    @ApiOperation(value = "删除角色", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "角色ID", required = true, dataType = "long")
    @DeleteMapping(value = "/authsec/role/{_id}")
    public GeneralResult delete(@PathVariable String _id) {
        return GeneralResult.success(this.roleService.delete(_id));
    }

    @ApiOperation(value = "修改角色", notes = "修改指定角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "_id", value = "角色ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "_role", value = "角色实体", required = true, dataType = "RoleForm")
    })
    @PutMapping(value = "/authsec/role/{_id}")
    public GeneralResult update(@PathVariable String _id, @Valid @RequestBody RoleForm _role) {
        Role tempRole = _role.toPo(_id, Role.class);
        return GeneralResult.success(this.roleService.update(tempRole));
    }

    @ApiOperation(value = "获取角色", notes = "获取指定角色信息")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "角色ID", required = true, dataType = "long")
    @GetMapping(value = "/authsec/role/{_id}")
    public GeneralResult get(@PathVariable String _id) {
        log.debug("get with id:{}", _id);
        return GeneralResult.success(this.roleService.get(_id));
    }

    @ApiOperation(value = "获取所有角色", notes = "获取所有角色")
    @GetMapping(value = "/authsec/role/all")
    public GeneralResult get() {
        return GeneralResult.success(this.roleService.getAll());
    }

    @ApiOperation(value = "查询角色", notes = "根据用户id查询用户所拥有的角色信息")
    @ApiImplicitParam(paramType = "path", name = "_userId", value = "用户id", required = true, dataType = "long")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @GetMapping(value = "/authsec/role/user/{_userId}")
    public GeneralResult query(@PathVariable Long _userId) {
        log.debug("query with userId:{}", _userId);
        return GeneralResult.success(this.roleService.queryByUserId(_userId));
    }

    @ApiOperation(value = "搜索角色", notes = "根据条件搜索角色信息")
    @ApiImplicitParam(name = "roleQueryForm", value = "角色查询参数", required = true, dataType = "RoleQueryForm")
    @ApiResponses(
            @ApiResponse(code = 100, message = "处理成功", response = GeneralResult.class)
    )
    @PostMapping(value = "/authsec/role/conditions")
    public GeneralResult query(@Valid @RequestBody RoleQueryForm _condition) {
        log.debug("query with name:{}", _condition);
        return GeneralResult.success(this.roleService.queryByUserId(_condition.getPage(), _condition.toParam(RoleQueryParam.class)));
    }
}
