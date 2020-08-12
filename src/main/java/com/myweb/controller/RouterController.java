package com.myweb.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.pojo.User;

/**
 * 僅控制各個頁面的轉發
 */
@Controller
@RequestMapping("/router")
public class RouterController {

    @Value("${mysession.user}")
    private String USER_SESSION;

    @PostMapping("/checkGoCloudManagement")
    public String checkGoCloudManagement(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(USER_SESSION);

        if(user !=null){
            return "cloud-management";
        }else{
            return "index";
        }
    }

    @PostMapping("/checkGoIndex")
    public String checkGoIndex(){
        return "index";
    }

}





















