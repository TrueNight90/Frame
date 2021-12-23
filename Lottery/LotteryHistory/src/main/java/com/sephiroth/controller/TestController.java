package com.sephiroth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api
@RestController("test")
public class TestController {

    public static Map map = new HashMap();
    public static byte[] HS_256_KEY = "sephiroth".getBytes();

    @ApiImplicitParam(name = "test",value = "测试",required = false)
    @ApiOperation(value = "测试接口")
    @GetMapping("test")
    public String test(){
        return "test";
    }

    @ApiImplicitParam(name = "key",value = "用户",required = false)
    @ApiOperation(value = "获取验证码图片")
    @GetMapping("pic")
    public Object pic(HttpServletRequest request){
        String key = request.getParameter("key");
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(1200, 300, 4, 1);
        lineCaptcha.setGenerator(new MathGenerator(4));
        System.out.println(lineCaptcha.getCode());
        map.put(key,lineCaptcha);
        return lineCaptcha.getImageBase64Data();
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "key",value = "用户",required = false),
                        @ApiImplicitParam(name = "code",value = "验证码",required = false)})
    @ApiOperation(value = "验证码验证")
    @GetMapping("verity")
    public Object verity(HttpServletRequest request){
        String key = request.getParameter("key");
        String code = request.getParameter("code");
        LineCaptcha lineCaptcha = (LineCaptcha)map.get(key);
        return lineCaptcha.verify(code);
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "user",value = "用户",required = false)
            })
    @ApiOperation(value = "获取token")
    @GetMapping("getToken")
    public Object getToken(HttpServletRequest request){
        String user = request.getParameter("user");
        Map map = new HashMap();
        map.put("user",user);
        String token = JWTUtil.createToken(map, HS_256_KEY);
        System.out.println(token);
        return token;
    }

    @ApiImplicitParams({@ApiImplicitParam(name = "token",value = "token",required = false)
    })
    @ApiOperation(value = "验证token")
    @GetMapping("verityToken")
    public Object verityToken(HttpServletRequest request){
        String token = request.getParameter("token");
        boolean verify = JWTUtil.verify(token, HS_256_KEY);
        if(verify){
            JWT jwt = JWTUtil.parseToken(token);
            System.out.println(jwt.getPayloads());
        }
        return verify;
    }

}
