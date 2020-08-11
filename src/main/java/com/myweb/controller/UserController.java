package com.myweb.controller;

import com.myweb.service.UserService;
import com.myweb.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseMessage login(@RequestParam(value = "username",required = false)String username,
                        @RequestParam(value = "password",required = false)String password){

        boolean result = userService.loginCheck(username, password);

        if(result == true){
            return new ResponseMessage().success();

        }else {
            return new ResponseMessage().error();
        }


    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseMessage register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("passwordAgain")String passwordAgain){

        boolean result = userService.registerCheck(username,password,passwordAgain);

        if(result == true){
            return new ResponseMessage().success();

        }else {
            return new ResponseMessage().error();
        }

    }

}












