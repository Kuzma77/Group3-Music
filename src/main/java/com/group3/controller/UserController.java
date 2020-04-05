package com.group3.controller;


import com.alibaba.fastjson.JSON;
import com.group3.entity.User;
import com.group3.service.UserService;
import com.group3.util.ResponseObject;
import com.group3.util.Salt;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.regex.Pattern;


/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */ 
@RestController
@RequestMapping(value = "/user")
public class UserController{
    @Resource
    private UserService userService;
    @RequestMapping(value = "register",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String register(@RequestParam(value = "username") String userName,@RequestParam(value = "account") String account){
        String REGEX_MOBILE = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
        String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        if(userService.getUserByPhoneNumber(account)==null&&userService.getUserByEmail(account)==null){
            String salt = Salt.getRandomSalt();
            String password = "123456";
            String newPassword = Salt.generate(password + salt, salt);
            if(Pattern.matches(REGEX_MOBILE,account)){
                User user = User.builder()
                        .userName(userName)
                        .password(newPassword)
                        .salt(salt)
                        .phoneNumber(account)
                        .binding(1)
                        .status(1)
                        .credits(0)
                        .createTime(LocalDateTime.now())
                        .lastLoginTime(LocalDateTime.now())
                        .build();
                userService.userSign(user);
                ResponseObject ro =  new ResponseObject(1,"通过手机号注册成功",200);
                return JSON.toJSONString(ro);
            }
            else if(Pattern.matches(REGEX_EMAIL,account)){
                User user = User.builder()
                        .userName(userName)
                        .password(newPassword)
                        .salt(salt)
                        .email(account)
                        .binding(2)
                        .status(1)
                        .credits(0)
                        .createTime(LocalDateTime.now())
                        .lastLoginTime(LocalDateTime.now())
                        .build();
                userService.userSign(user);
                ResponseObject ro =  new ResponseObject(1,"通过邮箱注册成功",200);
                return JSON.toJSONString(ro);
            }
            else {
                ResponseObject ro =  new ResponseObject(1,"请输入正确格式的手机号或者邮箱",201);
                return JSON.toJSONString(ro);
            }
        }
        else {
            ResponseObject ro =  new ResponseObject(1,"该手机号或邮箱已经被注册",301);
            return JSON.toJSONString(ro);
        }
    }
    @RequestMapping(value = "logon",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String login(@RequestParam(value = "account") String account,@RequestParam(value = "password") String password){
            if(userService.userLogin(account)!=null){
                User user = userService.userLogin(account);String salt = user.getSalt();
                String realPassword = Salt.generate(password+salt,salt);
                if(realPassword.equals(user.getPassword())){
                    LocalDateTime lastLoginTime = user.getLastLoginTime();
                    if(Duration.between(lastLoginTime,LocalDateTime.now()).toDays()>=1 || user.getCredits()==0){
                        user.setCredits(user.getCredits()+5);
                        user.setLastLoginTime(LocalDateTime.now());
                        userService.updateCredits(user);
                    }
                    ResponseObject ro =  new ResponseObject(1,"登录成功",200);
                    return JSON.toJSONString(ro);
                }
                else {
                    ResponseObject ro =  new ResponseObject(1,"密码错误",400);
                    return JSON.toJSONString(ro);
                }
            }
            else {
                ResponseObject ro =  new ResponseObject(1,"尚未注册",404);
                return JSON.toJSONString(ro);
            }
    }
    @RequestMapping(value = "cancelUser",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    public String cancelUser(@RequestParam(value = "userId") Integer userId){
        if(userService.getUserById(userId)!=null){
            userService.cancelUser(userId);
            ResponseObject ro =  new ResponseObject(1,"注销成功",200);
            return JSON.toJSONString(ro);
        }
        else {
            ResponseObject ro =  new ResponseObject(1,"注销失败",404);
            return JSON.toJSONString(ro);
        }
    }
    @RequestMapping(value = "updatePassword",method = RequestMethod.PUT,produces = "application/json;charset=utf-8")
    public String updatePassword(@RequestParam(value = "userId") Integer userId , @RequestParam(value = "newPassword") String newPassword){
        if(userService.getUserById(userId)!=null){
            if(newPassword.length()==6){
                User user = userService.getUserById(userId);
                String salt = Salt.getRandomSalt();
                String newPrd = Salt.generate(newPassword + salt, salt);
                user.setPassword(newPrd);
                user.setSalt(salt);
                userService.updatePassword(user);
                ResponseObject ro =  new ResponseObject(1,"修改成功",200);
                return JSON.toJSONString(ro);
            }
           else {
                ResponseObject ro =  new ResponseObject(1,"请输入六位数密码",401);
                return JSON.toJSONString(ro);
            }
        }
        else {
            ResponseObject ro =  new ResponseObject(1,"修改失败",404);
            return JSON.toJSONString(ro);
        }
    }
}
