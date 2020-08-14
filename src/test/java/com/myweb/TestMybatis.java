package com.myweb;

import com.myweb.dao.UserFilesMapper;
import com.myweb.dao.UserMapper;
import com.myweb.dao.UserStoreMapper;
import com.myweb.pojo.User;
import com.myweb.pojo.UserFiles;
import com.myweb.pojo.UserStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TestMybatis {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserStoreMapper userStoreMapper;

    @Autowired
    UserFilesMapper userFilePathMapper;

    @Autowired
    UserFilesMapper userFilesMapper;

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
        UserStore userStoreById = userStoreMapper.selectById(1);
        System.out.println(userStoreById);

        User user = userMapper.selectById(1);
        System.out.println(user);

    }

    @Test
    public void test(){
        UserFiles userFiles = userFilesMapper.selectFileByIdAndFileName(1, "cloud1597410967373.png");
        System.out.println(userFiles);

        userFiles.setDownloadTime(userFiles.getDownloadTime() + 1);

        userFilesMapper.update(userFiles,null);
    }

}













