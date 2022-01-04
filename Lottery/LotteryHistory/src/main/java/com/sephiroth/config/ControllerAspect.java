package com.sephiroth.config;

import cn.hutool.core.codec.Base64;
import com.alibaba.fastjson.JSON;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * aop 增强
 */
@Aspect
@Component
public class ControllerAspect {

//    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    //RestController类下所有方法
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) " +
            "|| @annotation(org.springframework.web.bind.annotation.ResponseBody)")
    public void logPointCut() {
        System.out.println("pointCut");
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        System.out.println("around");
        //方法的参数
        Object[] args = point.getArgs();
        String[] parameterNames = ((CodeSignature) point.getSignature()).getParameterNames();
        for(int i = 0; i < parameterNames.length ; i++){
            System.out.println(parameterNames[i]+"="+args[i]);
        }
        //方法的返回值
        Object proceed = point.proceed();
        String encode = Base64.encode(JSON.toJSONString(proceed));
        System.out.println(encode);
        return encode;
    }

    @Before("logPointCut()")
    public void before(){
        System.out.println("before");
    }

    @After("logPointCut()")
    public void after(){
        System.out.println("after");
    }
}
