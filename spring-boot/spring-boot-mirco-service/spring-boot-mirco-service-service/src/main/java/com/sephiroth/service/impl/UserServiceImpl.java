package com.sephiroth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sephiroth.api.IUserService;
import com.sephiroth.mapper.UserMapper;
import com.sephiroth.po.User;
import org.apache.dubbo.config.annotation.DubboService;

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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
