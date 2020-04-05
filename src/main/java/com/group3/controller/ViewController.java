package com.group3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wl_sun
 * @description TODO
 * @create Date
 */
@Controller
@RequestMapping(value = "/")
public class ViewController {

    @GetMapping(value = "home")
    public String home(Model model){
        model.addAttribute("welcome","Welcome");
        return "home";
    }
}
