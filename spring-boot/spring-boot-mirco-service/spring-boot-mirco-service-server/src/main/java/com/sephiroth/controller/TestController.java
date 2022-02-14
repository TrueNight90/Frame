package com.sephiroth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TestController {

    @GetMapping("test")
    public Object test(){
        return "test";
    }

    @GetMapping("sessionId")
    public Object session(HttpServletRequest request){
        return request.getSession().getId();
    }
}
