package com.myweb.controller;

import com.myweb.service.UserService;
import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;

/**
 * 處理使用者相關登入、註冊等功能
 * */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    ConfigurationFactory configurationFactory;

    @Autowired
    UserService userService;




    //登錄功能
    @RequestMapping("/login")
    @ResponseBody
    public ModelAndView login(@RequestParam("username")String username,
                              @RequestParam("password")String password,
                              HttpServletResponse response,
                              HttpServletRequest request){

        boolean result = userService.loginCheck(username, password,request,response);

        ModelAndView modelAndView = new ModelAndView();
        if(result == true){
            modelAndView.setViewName("cloud-management");

            //redirect:/templates/cloud-management.html

        }else if(result == false){
            modelAndView.setViewName("index");
            modelAndView.addObject("msg","登入失敗，請確認帳號密碼正確");
        }

        return modelAndView;

    }


    //註冊功能
    @PostMapping("/register")
    @ResponseBody
    public ModelAndView register(@RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("passwordAgain")String passwordAgain,
                                 HttpServletRequest request){

        boolean result = userService.registerCheck(username,password,passwordAgain,request);

        ModelAndView modelAndView = new ModelAndView();

        if(result == true){
            modelAndView.setViewName("index");
            modelAndView.addObject("msg","註冊成功，您現在可以進行登錄");

        }else if(result == false){
            modelAndView.setViewName("index");
            modelAndView.addObject("msg","註冊失敗，驗證錯誤或帳號重複");
        }

        return modelAndView;

    }


    //登出功能
    @GetMapping("/logOut")
    public String logout(HttpServletRequest request){

        System.out.println("logout");

        HttpSession session = request.getSession();
        session.removeAttribute(configurationFactory.getUserSession());

        return "index";
    }
}












