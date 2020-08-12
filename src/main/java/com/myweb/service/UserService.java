package com.myweb.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {

    //登入判斷
    public boolean loginCheck(String username, String password, HttpServletRequest request, HttpServletResponse response);

    //註冊判斷
    public boolean registerCheck(String username,String password,String passwordAgain);

}
