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

    @Override
    public User userLogin(String account) {
        return userMapper.userLogin(account);
    }

    @Override
    public User getUserByPhoneNumber(String phoneNumber) {
        return userMapper.getUserByPhoneNumber(phoneNumber);
    }

    @Override
    public User getUserByEmail(String email) {
        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserById(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public void cancelUser(Integer userId) {
        userMapper.cancelUser(userId);
    }

    @Override
    public void updateCredits(User user) {
        userMapper.updateCredits(user);
    }

    @Override
    public void updatePassword(User user) {
        userMapper.updatePassword(user);
    }
}
