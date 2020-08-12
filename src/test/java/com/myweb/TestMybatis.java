package com.myweb;

import com.myweb.dao.UserFilePathMapper;
import com.myweb.dao.UserMapper;
import com.myweb.pojo.User;
import com.myweb.pojo.UserFilePath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class TestMybatis {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserFilePathMapper userFilePathMapper;

    @Test
    public void TestMybatisPlus(){
        User user = new User();

        user.setName("myname");
        user.setUsername("admin");
        user.setPassword("admin");

        userMapper.insert(user);

    }

    @Test
    public void TestUserFilePath(){

        Map map = new HashMap<String,String>();
        map.put("test","test");

        UserFilePath userFilePath = new UserFilePath();
        JSONObject document = new JSONObject(map);
        userFilePath.setId(1);
        userFilePath.setDocument(document);

        userFilePathMapper.insert(userFilePath);



    }

}













