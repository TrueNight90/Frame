package com.sephiroth.controller;

import com.sephiroth.service.IUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @DubboReference
    IUserService iUserService;

    @GetMapping("test")
    public Object test(){
        return "test";
    }

    @GetMapping("sessionId")
    public Object session(HttpServletRequest request){
        return request.getSession().getId();
    }

    @GetMapping("getUserList")
    public Object getUserList(){
        return iUserService.list();
    }
}
