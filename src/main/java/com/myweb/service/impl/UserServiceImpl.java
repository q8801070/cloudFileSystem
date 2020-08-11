package com.myweb.service.impl;

import com.myweb.dao.UserMapper;
import com.myweb.pojo.User;
import com.myweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean loginCheck(String username, String password) {

        List<User> users = userMapper.selectList(null);

        if(username.length() <8 || username.length() >30 || password.length()<8 || password.length() >30){
            return false;
        }

        for (User user : users) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean registerCheck(String username, String password, String passwordAgain) {

        List<User> users = userMapper.selectList(null);

        if(username.length() <8 || username.length() >30 || password.length()<8 || password.length() >30){
            return false;
        }

        for (User user : users) {
            if(username.equals(user.getUsername())){
                return false;
            }
        }

        if(password.equals(passwordAgain) == false){
            return false;
        }

        //新增使用者
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(username);

        userMapper.insert(user);
        return true;
    }
}


