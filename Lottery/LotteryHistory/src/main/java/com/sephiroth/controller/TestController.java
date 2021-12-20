package com.sephiroth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class TestController {

    @ApiImplicitParam(name = "test",value = "测试",required = false)
    @ApiOperation(value = "测试接口")
    @GetMapping("test")
    public String test(){
        return "test";
    }
}
