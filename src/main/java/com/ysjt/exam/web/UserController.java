package com.ysjt.exam.web;

import com.ysjt.exam.aop.Public;
import com.ysjt.exam.common.entity.ExamUser;
import com.ysjt.exam.common.response.Response;
import com.ysjt.exam.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Api(description = "用户接口")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation("所有用户信息")
    @GetMapping("list")
    public Response listAllUser() {
        return Response.ok(userService.listAll());
    }

    @Public
    @ApiOperation("登录")
    @PostMapping("login")
    public Response login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return Response.ok(userService.login(username, password));
    }

    @Public
    @ApiOperation("注册")
    @PostMapping("register")
    public Response register(@Valid @RequestBody ExamUser user) {
        userService.register(user);
        return Response.ok();
    }
}
