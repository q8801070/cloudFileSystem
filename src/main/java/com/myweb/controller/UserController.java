package com.myweb.controller;

import com.myweb.service.UserService;
import com.myweb.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public ResponseMessage login(@RequestParam(value = "username")String username,
                                 @RequestParam(value = "password")String password,
                                 HttpServletResponse response,
                                 HttpServletRequest request){

        System.out.println("here");

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












