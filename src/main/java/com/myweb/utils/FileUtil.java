package com.myweb.utils;


import com.myweb.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 檔案相關功能
 */
@Component
public class FileUtil {

    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER;

    //建立使用者儲存空間
    public void createUserStore(User user) throws IOException {
        //建立的資料夾名稱為使用者的id
        Path p = Paths.get(UPLOADED_FOLDER + user.getId());

        System.out.println("UPLOAD_FOLDER + user.getId()=" + UPLOADED_FOLDER + user.getId());
        System.out.println("p=" + p);

        //檢查資料夾是否已經存在
        if(!Files.exists(p)){
            //如果不存在，替該使用者建立資料夾
            System.out.println("here");
            Files.createDirectory(p);
        }


    }

}














