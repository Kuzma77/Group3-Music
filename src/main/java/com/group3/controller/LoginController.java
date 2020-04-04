package com.group3.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
@RestController("com.group3.config")
@RequestMapping(value = "/")
public class LoginController{
    @GetMapping("/home")
    public String login(Model model){
        model.addAttribute("message","Keep Healthy!");
        return "login";
    }
}