package com.myweb.service.impl;

import com.myweb.dao.UserFilesMapper;
import com.myweb.dao.UserMapper;
import com.myweb.dao.UserStoreMapper;
import com.myweb.pojo.User;
import com.myweb.pojo.UserStore;
import com.myweb.service.UserService;
import com.myweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Value("${mysession.user}")
    private String USER_SESSION;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserStoreMapper userStoreMapper;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public boolean loginCheck(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        List<User> users = userMapper.selectList(null);

        //檢查登入條件
        if(username.length() <8 || username.length() >30 || password.length()<8 || password.length() >30){
            return false;
        }

        //檢查帳號密碼是否一致
        for (User user : users) {
            if(username.equals(user.getUsername()) && password.equals(user.getPassword())){
                //建立Session
                HttpSession session = request.getSession();
                session.setAttribute(USER_SESSION,userMapper.selectById(user));

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean registerCheck(String username, String password, String passwordAgain){

        List<User> users = userMapper.selectList(null);

        //檢查規則
        if(username.length() <8 || username.length() >30 || password.length()<8 || password.length() >30){
            return false;
        }

        //檢查是否有重複帳號
        for (User user : users) {
            if(username.equals(user.getUsername())){
                return false;
            }
        }

        //檢查兩次輸入的密碼是否相同
        if(password.equals(passwordAgain) == false){
            return false;
        }

        //新增使用者
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setName(username);

        userMapper.insert(user);

        //替新使用者創建雲端硬體空間
        UserStore userStore = new UserStore();
        userStore.setMax_size(1073741824);
        userStore.setCurrent_size(0);
        userStore.setId(userMapper.selectByUsername(username).getId()); //去資料庫找尋該帳號的使用者，並且創建硬碟空間
        userStoreMapper.insert(userStore);

        //建立專屬資料夾
        try {
            fileUtil.createUserStore(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}


