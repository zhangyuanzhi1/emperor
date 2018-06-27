package com.als.authorize.sso.controller;

import com.als.authorize.sso.common.bean.ApiResult;
import com.als.authorize.sso.controller.model.UserModel;
import com.als.authorize.sso.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("用户服务")
@RestController
@RequestMapping("user")
public class UserController extends BasicController {
    @Autowired
    private UserService userService;

    @ApiOperation("新增用户")
    @ApiResponses(@ApiResponse(code = 200, message = "新增用户"))
    @RequestMapping(path = "add", method = RequestMethod.POST)
    public ApiResult<UserModel> add(@ApiParam(value = "用户名", required = true) @RequestBody String username,
                                    @ApiParam(value = "密码", required = true) @RequestBody String password) {
        UserModel userModel = userService.add(username, password);
        return new ApiResult<>(userModel);
    }

    @ApiOperation("禁用/启用用户")
    @ApiResponses(@ApiResponse(code = 200, message = "禁用/启用用户"))
    @RequestMapping(path = "enable/{userId}", method = RequestMethod.POST)
    public ApiResult enable(@ApiParam(value = "用户ID", required = true) @PathVariable long userId,
                            @ApiParam(value = "是否启用", required = true) @RequestBody boolean enabled) {
        userService.enable(userId, enabled);
        return new ApiResult();
    }

    @ApiOperation("删除用户")
    @ApiResponses(@ApiResponse(code = 200, message = "删除用户"))
    @RequestMapping(path = "delete/{userId}", method = RequestMethod.POST)
    public ApiResult delete(@ApiParam(value = "用户ID", required = true) @PathVariable long userId) {
        userService.delete(userId);
        return new ApiResult();
    }

    @ApiOperation("重置密码")
    @ApiResponses(@ApiResponse(code = 200, message = "重置密码"))
    @RequestMapping(path = "resetPassword/{userId}", method = RequestMethod.POST)
    public ApiResult resetPassword(@ApiParam(value = "用户ID", required = true) @PathVariable long userId,
                                   @ApiParam(value = "新密码", required = true) @RequestBody String password) {
        userService.resetPassword(userId, password);
        return new ApiResult();
    }
}
