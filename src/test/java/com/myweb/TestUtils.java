package com.myweb;


import com.myweb.utils.FileUtil;
import com.myweb.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest
public class TestUtils {

    @Autowired
    FileUtil fileUtil;

    @Test
    public void TestCreateStore(){
        User user = new User();
        user.setId(1);

        try {
            fileUtil.createUserStore(user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestGetFileSize(){
        int userFileCurrentSize = fileUtil.getUserFileCurrentSize(1,FileUtil.DOCUMENT);
        System.out.println(userFileCurrentSize);
    }

    @Test
    public void TestGetFileCount(){
        System.out.println(fileUtil.getUserFileCount(1, FileUtil.IMAGE));



    }


}
