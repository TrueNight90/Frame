package com.sephiroth.service.impl;

import com.sephiroth.api.ITestUserService;
import com.sephiroth.mapper.TestUserMapper;
import com.sephiroth.po.User;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

@DubboService
public class TestUserServiceImpl implements ITestUserService {

    @Autowired
    TestUserMapper testUserMapper;

    @Override
    public int insertUser(User user) {
        return testUserMapper.insertUser(user);
    }
}
