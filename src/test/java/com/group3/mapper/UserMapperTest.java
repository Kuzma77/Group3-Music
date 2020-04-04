package com.group3.mapper;


import com.group3.config.MapperConfig;
import com.group3.entity.User;
import com.group3.util.Salt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MapperConfig.class})
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;
    @Test
    public void userLogin() {
        String account = "18805167526";
        String password = "admin";
        if(userMapper.userLogin(account)!=null){
            User user = userMapper.userLogin(account);
            String salt = user.getSalt();
            String realPassword = Salt.generate(password+salt,salt);
            if(realPassword.equals(user.getPassword())){
                LocalDateTime lastLoginTime = user.getLastLoginTime();
                if(Duration.between(lastLoginTime,LocalDateTime.now()).toDays()>=1 || user.getCredits()==0){
                    user.setCredits(user.getCredits()+5);
                    user.setLastLoginTime(LocalDateTime.now());
                    userMapper.updateCredits(user);
                }
                System.out.println("登录成功");
            }
            else {
                System.out.println("密码错误");
            }
        }
        else {
            System.out.println("未注册");
        }
    }
    @Test
    public void userSign() {
        String name = "小黑";
        String password = "admin";
        String account = "18805167526";
        String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(userMapper.getUserByPhoneNumber(account)==null&&userMapper.getUserByEmail(account)==null){
            String salt = Salt.getRandomSalt();
            String newPassword = Salt.generate(password + salt, salt);
            if(Pattern.matches(REGEX_MOBILE,account)){
                User user = User.builder()
                        .userName(name)
                        .password(newPassword)
                        .salt(salt)
                        .phoneNumber(account)
                        .binding(1)
                        .status(1)
                        .credits(0)
                        .createTime(LocalDateTime.now())
                        .lastLoginTime(LocalDateTime.now())
                        .build();
                userMapper.userSign(user);
                System.out.println("注册成功！");
            }
            else if(Pattern.matches(REGEX_EMAIL,account)){
                User user = User.builder()
                        .userName(name)
                        .password(newPassword)
                        .salt(salt)
                        .email(account)
                        .binding(2)
                        .status(1)
                        .credits(0)
                        .createTime(LocalDateTime.now())
                        .lastLoginTime(LocalDateTime.now())
                        .build();
                userMapper.userSign(user);
                System.out.println("注册成功！");
            }
            else {
                System.out.println("请输入正确的手机号或者邮箱");
            }
        }
        else {
            System.out.println("该手机号已被注册");
        }
    }

    @Test
    public void getUserByPhoneNumber() {
    }

    @Test
    public void getUserByEmail() {
    }



    @Test
    public void canaleUser() {
        User user = userMapper.userLogin("18805167526");
        userMapper.cancelUser(user.getId());
    }

    @Test
    public void loveMusic() {
    }

    @Test
    public void batchLoveMusic() {
    }

    @Test
    public void cancleLoveMusic() {
    }

    @Test
    public void batchcancleLoveMusic() {
    }

    @Test
    public void getMusicById() {
    }

    @Test
    public void getMusicByKeyWord() {
    }

    @Test
    public void getMusicListBiUserId() {
    }

    @Test
    public void selectAll() {
        System.out.println(userMapper.selectAll());
    }

    @Test
    public void updatePassword() {
        User user = userMapper.userLogin("18805167526");
        String salt = Salt.getRandomSalt();
        String newPassword = Salt.generate(user.getPassword(),salt);
        user.setPassword(newPassword);
        user.setSalt(salt);
        userMapper.updatePassword(user);
    }
}
