package com.myweb;


import com.myweb.model.UsingSituationModel;
import com.myweb.service.UsingSituationService;
import com.myweb.utils.FileUtil;
import com.myweb.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class TestUtils {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UsingSituationService usingSituationService;

    @Test
    public void TestCreateStore(){
        User user = new User();
        user.setId(2);

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

    @Test
    public void Test(){
        UsingSituationModel usingSituationModel = usingSituationService.refreshPage(1);
        System.out.println(usingSituationModel);
    }

    @Test
    public void test(){
        UsingSituationModel usingSituationModel = usingSituationService.refreshPage(1);
        System.out.println(usingSituationModel);
    }

    @Test
    public void testFileExist(){
        boolean fileExist = fileUtil.isFileExist(1, "clod.png");
        System.out.println(fileExist);
    }

    @Test
    public void testGetTime(){
        System.out.println(System.currentTimeMillis());
    }



}













