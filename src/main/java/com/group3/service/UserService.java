package com.group3.service;

import com.group3.entity.User;



/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
public interface UserService {
    /**
     * 注册一个用户
     *
     * @param user
     */
    void userSign(User user);
    /**
     * 用户登录
     *
     * @param account
     * @return User
     */
   User userLogin(String account);
    /**
     * 通过用户手机号查询用户
     * @param phoneNumber
     * @return User
     */
    User getUserByPhoneNumber(String phoneNumber);
    /**
     * 通过用户邮箱查询用户
     *
     * @param email
     * @return User
     */
    User getUserByEmail(String email);
    /**
     * 通过id获得用户
     * @param userId
     * @return
     */
    User getUserById(Integer userId);
    /**
     * 注销用户
     *
     * @param userId
     */
    void cancelUser(Integer userId);
    /**
     * 更新积分和登录时间
     *
     * @param user
     */
    void updateCredits(User user);
    /**
     * 修改密码
     *
     * @param user
     */
    void updatePassword(User user);
}
