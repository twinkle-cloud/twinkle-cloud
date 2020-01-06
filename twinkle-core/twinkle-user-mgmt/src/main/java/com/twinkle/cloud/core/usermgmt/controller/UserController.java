package com.twinkle.cloud.core.usermgmt.controller;

import com.twinkle.cloud.common.data.GeneralResult;
import com.twinkle.cloud.core.usermgmt.entity.UserInfo;
import com.twinkle.cloud.core.usermgmt.entity.dto.PasswordUpdateRequest;
import com.twinkle.cloud.core.usermgmt.entity.dto.UserRequest;
import com.twinkle.cloud.core.usermgmt.entity.query.UserPageQuery;
import com.twinkle.cloud.core.usermgmt.entity.query.UserQuery;
import com.twinkle.cloud.core.usermgmt.entity.User;
import com.twinkle.cloud.core.usermgmt.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     12/31/19 4:40 PM<br/>
 *
 * @author chenxj
 * @see
 * @since JDK 1.8
 */
@RestController
@Api("User Management")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "新增用户", notes = "新增一个用户")
    @ApiImplicitParam(name = "Register User Info", value = "新增用户form表单", required = true, dataType = "UserForm")
    @PostMapping(value = "/noauth/user")
    public GeneralResult add(@Valid @RequestBody UserRequest _userInfo) {
        log.debug("User Info:{}", _userInfo);
        User tempUser = _userInfo.toPo(User.class);
        UserInfo tempUserInfo = _userInfo.parseUserInfo();
        tempUser.setUserInfo(tempUserInfo);
        return GeneralResult.success(this.userService.add(tempUser));
    }

    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象，逻辑删除")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "用户ID", required = true, dataType = "string")
    @DeleteMapping(value = "/authsec/user/{_id}")
    public GeneralResult delete(@PathVariable Long _id) {
        return GeneralResult.success(this.userService.delete(_id));
    }

    @ApiOperation(value = "修改用户", notes = "修改指定用户信息")
    @ApiImplicitParams({@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "string"),
            @ApiImplicitParam(name = "userUpdateForm", value = "用户实体", required = true, dataType = "UserForm")})
    @PutMapping(value = "/authsec/user/{_id}")
    public GeneralResult update(@PathVariable Long _id, @Valid @RequestBody UserRequest _userInfo) {
        User tempUser = _userInfo.toPo(User.class);
        tempUser.setId(_id);
        UserInfo tempUserInfo = _userInfo.parseUserInfo();
        tempUser.setUserInfo(tempUserInfo);
        return GeneralResult.success(this.userService.update(tempUser));
    }

    @ApiOperation(value = "获取用户", notes = "获取指定用户信息")
    @ApiImplicitParam(paramType = "path", name = "_id", value = "用户ID", required = true, dataType = "string")
    @GetMapping(value = "/authsec/user/{_id}")
    public GeneralResult get(@PathVariable Long _id) {
        log.debug("get with id:{}", _id);
        return GeneralResult.success(this.userService.get(_id));
    }

    @ApiOperation(value = "获取用户", notes = "根据用户唯一标识（LoginName or Phone）获取用户信息")
    @ApiImplicitParam(paramType = "query", name = "_uniqueId", value = "用户唯一标识", required = true, dataType = "string")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class))
    @GetMapping(value = "/noauth/user")
    public GeneralResult query(@RequestParam String _uniqueId) {
        log.debug("query with username or mobile:{}", _uniqueId);
        return GeneralResult.success(this.userService.getByUniqueId(_uniqueId));
    }

    @ApiOperation(value = "搜索用户", notes = "根据条件查询用户信息")
    @ApiImplicitParam(name = "userQueryForm", value = "用户查询参数", required = true, dataType = "UserQueryForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class))
    @PostMapping(value = "/authsec/user/conditions")
    public GeneralResult search(@Valid @RequestBody UserPageQuery _condition) {
        log.debug("Search User with condition:{}", _condition);
        return GeneralResult.success(this.userService.query(_condition.getPage(), _condition.toParam(UserQuery.class)));
    }

    @ApiOperation(value = "更新密码", notes = "更新用户密码")
    @ApiImplicitParam(name = "Update Password", value = "用户查询参数", required = true, dataType = "PasswordUpdateForm")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class))
    @PutMapping(value = "/authsec/user/password")
    public GeneralResult updatePassword(@Valid @RequestBody PasswordUpdateRequest _password) {
        log.debug("Update the [{}]'s password.", _password.getUserId());
        return GeneralResult.success(this.userService.updatePassword(_password));
    }

    @ApiOperation(value = "管理员重置密码", notes = "更新用户密码")
    @ApiImplicitParam(paramType = "path", name = "_userId", value = "用户ID", required = true, dataType = "string")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = GeneralResult.class))
    @GetMapping(value = "/authsec/admin/user/{_userId}/password/reset")
    public GeneralResult resetPassword(@PathVariable Long _userId) {
        log.debug("Reset the [{}]'s password.", _userId);
        return GeneralResult.success(this.userService.resetPassword(_userId));
    }
}
