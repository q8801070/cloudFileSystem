package com.myweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 處理使用者相關登入、註冊等功能
 * */
@Controller
@RequestMapping("/user")
public class UserController {

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam(value = "username",required = false)String username,
                        @RequestParam(value = "password",required = false)String password){
        System.out.println(username);
        System.out.println(password);

        return "success";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("passwordAgain")String passwordAgain){
        System.out.println(username);
        System.out.println(password);
        System.out.println(passwordAgain);

        return "success";
    }

}












