package com.sephiroth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sephiroth.entity.User;
import com.sephiroth.mapper.UserMapper;
import com.sephiroth.service.IUserService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sephiroth
 * @since 2022-01-17
 */
@DubboService
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
