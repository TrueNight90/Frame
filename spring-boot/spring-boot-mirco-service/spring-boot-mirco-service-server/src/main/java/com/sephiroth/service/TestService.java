package com.sephiroth.service;

import com.sephiroth.api.ITestUserService;
import com.sephiroth.api.IUserService;
import com.sephiroth.po.User;
import com.sephiroth.service.api.ISendMessageService;
//import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @DubboReference
    IUserService iUserService;

    @DubboReference
    ITestUserService iTestUserService;

    @DubboReference
    ISendMessageService iSendMessageService;


//    @GlobalTransactional
    public void testSeate(String s) throws Exception {
        User user = new User();
        user.setName(s);
        boolean b = iUserService.saveOrUpdate(user);
        if("wangwu".equals(s)){
            iSendMessageService.send(123);
        }else{
            iSendMessageService.send(s);
        }
    }
}
