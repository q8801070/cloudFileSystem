package com.myweb.controller;


import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.pojo.User;

/**
 * 控制各個頁面的轉發
 */
@Controller
@RequestMapping("/router")
public class RouterController {

    @Autowired
    ConfigurationFactory configurationFactory;

    //前往雲端管理頁面
    @RequestMapping("/checkGoCloudManagement")
    public String checkGoCloudManagement(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(configurationFactory.getUserSession());

        if(user !=null){
            return "cloud-management";
        }else{
            return "index";
        }
    }

    //前往首頁
    @PostMapping("/checkGoIndex")
    public String checkGoIndex(){
        return "index";
    }

    //前往使用情況頁面
    @RequestMapping("/checkGoUsingSituation")
    public String checkGoUsingSituation(){
        return "using-situation";
    }

    //前往檔案上傳頁面
    @PostMapping("/checkGoFileUpload")
    public String checkGoFileUpload(){
        return "file-upload";
    }

    //前往所有檔案、我的文件、我的圖片、我的影片、我的音樂、其他文件的共通頁面，透過cookie代號決定讀哪種參數
    @PostMapping("/checkGoAutoSortFiles/{pathNumber}")
    public String checkGoAutoSortFiles(@PathVariable("pathNumber")String pathNumber,HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = new Cookie(configurationFactory.getShowFilePageNumber(),pathNumber);
        cookie.setMaxAge(300);
        cookie.setPath("/");
        response.addCookie(cookie);

        return "auto-sort-files";
    }

}





















