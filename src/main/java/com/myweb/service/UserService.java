package com.myweb.service;

public interface UserService {

    public boolean loginCheck(String username,String password);

    public boolean registerCheck(String username,String password,String passwordAgain);

}
