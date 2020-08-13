package com.myweb.controller;


import com.myweb.utils.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    SessionFactory sessionFactory;

    //嘗試前往雲端管理頁面
    @PostMapping("/checkGoCloudManagement")
    public String checkGoCloudManagement(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(sessionFactory.getUserSession());

        if(user !=null){
            return "cloud-management";
        }else{
            return "index";
        }
    }

    //嘗試前往首頁
    @PostMapping("/checkGoIndex")
    public String checkGoIndex(){
        return "index";
    }

}





















