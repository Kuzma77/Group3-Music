package com.group3.service.impl;

import com.group3.entity.User;
import com.group3.mapper.UserMapper;
import com.group3.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public void userSign(User user) {
        userMapper.userSign(user);
    }
}
