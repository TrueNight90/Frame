package com.sephiroth.controller;

import com.sephiroth.api.ITestUserService;
import com.sephiroth.api.IUserService;
import com.sephiroth.po.User;
import com.sephiroth.service.TestService;
import com.sephiroth.service.api.ISendMessageService;
import io.seata.core.context.RootContext;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    TestService testService;

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
        try{
            iSendMessageService.send(s);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("testSeate")
    public Object testSeate(String s){
        try{
            System.out.println("testSeate Server Begin ... xid: " + RootContext.getXID() + "\n");
            iUserService.testSeata(s);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
