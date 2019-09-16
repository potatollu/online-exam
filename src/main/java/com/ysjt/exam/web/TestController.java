package com.ysjt.exam.web;

import com.ysjt.exam.aop.Public;
import com.ysjt.exam.common.response.Response;
import com.ysjt.exam.common.response.ResponseX;
import com.ysjt.exam.exception.base.BusinessException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Api(description = "test接口")
@RestController
public class TestController {

    @ApiOperation(value="test", notes="test")
    @GetMapping("hello")
    public Response test() {
        return Response.ok("hello world");
    }

    @Public
    @ApiOperation(value="testException")
    @GetMapping("exception")
    public Response testException() {
        System.out.println(1/0);
//        throw new BusinessException(414, "exception success");
        return Response.ok();
    }

    @GetMapping("test/header")
    public Response testHeader(@RequestHeader("userId") Long userId, @RequestHeader("username") String username) {
        return Response.ok(userId + "," +username);
    }

    @GetMapping("test/testParam")
    public Response testParam(@RequestParam("id") Long id) {
        return Response.ok(id);
    }
}
