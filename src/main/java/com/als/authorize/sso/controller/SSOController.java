package com.als.authorize.sso.controller;

import com.als.authorize.sso.common.bean.ApiResult;
import com.als.authorize.sso.controller.model.UserModel;
import com.als.authorize.sso.service.SSOService;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("单点登录")
@RestController
@RequestMapping("sso")
public class SSOController extends BasicController {
    @Autowired
    private SSOService ssoService;

    @ApiOperation("登录")
    @ApiResponses(@ApiResponse(code = 200, message = "登录"))
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public ApiResult<UserModel> login(@ApiParam(value = "用户名", required = true) @RequestBody String username,
                                      @ApiParam(value = "密码", required = true) @RequestBody String password) {
        UserModel userModel = ssoService.login(username, password);
        return new ApiResult<>(userModel);
    }

    @ApiOperation("登出")
    @ApiResponses(@ApiResponse(code = 200, message = "登出"))
    @RequestMapping(path = "logout", method = RequestMethod.POST)
    public ApiResult logout(@ApiParam(value = "用户ID", required = true) @RequestBody long userId,
                            @ApiParam("令牌") @RequestBody(required = false) String token) {
        if (StringUtils.isEmpty(token)) {
            ssoService.logout(userId);
        } else {
            ssoService.logout(userId, token);
        }
        return new ApiResult();
    }

    @ApiOperation("检查登录状态")
    @ApiResponses(@ApiResponse(code = 200, message = "检查登录状态"))
    @RequestMapping(path = "checkLogin", method = RequestMethod.GET)
    public ApiResult checkLogin(@ApiParam(value = "用户ID", required = true) @RequestParam long userId,
                                @ApiParam(value = "令牌", required = true) @RequestParam String token) {
        if (ssoService.checkLogin(userId, token)) {
            return new ApiResult();
        } else {
            return new ApiResult(500, "未登录或登录超时");
        }
    }
}
