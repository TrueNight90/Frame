package com.sephiroth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sephiroth.api.IUserService;
import com.sephiroth.mapper.UserMapper;
import com.sephiroth.po.User;
import com.sephiroth.service.api.ISendMessageService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sephiroth
 * @since 2022-01-17
 */
//@Service已过时
@DubboService
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @DubboReference()
    ISendMessageService iSendMessageService;

    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public Object testSeata(String s) throws Exception {
        System.out.println("Assign Service Begin ... xid: " + RootContext.getXID() + "\n");
        User user = new User();
        user.setName(s);
        boolean b = saveOrUpdate(user);
        if("wangwu".equals(s)){
            iSendMessageService.send(123);
        }else{
            iSendMessageService.send(s);
        }
        return "ok";
    }
}
