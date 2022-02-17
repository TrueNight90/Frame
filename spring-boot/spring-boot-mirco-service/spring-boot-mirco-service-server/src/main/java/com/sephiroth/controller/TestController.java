package com.sephiroth.controller;

import com.sephiroth.api.ITestUserService;
import com.sephiroth.api.IUserService;
import com.sephiroth.po.User;
import com.sephiroth.service.api.ISendMessageService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @DubboReference
    IUserService iUserService;

    @DubboReference
    ITestUserService iTestUserService;

    @DubboReference
    ISendMessageService iSendMessageService;

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

    @GetMapping("insertUser")
    public Object insertUser(User user){
        return iTestUserService.insertUser(user);
    }

    @GetMapping("send")
    public Object send(String s){
        iSendMessageService.send(s);
        return "ok";
    }
}
