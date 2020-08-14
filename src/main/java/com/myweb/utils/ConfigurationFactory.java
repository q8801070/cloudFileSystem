package com.myweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Session管理工廠類
 */
@Component
public class ConfigurationFactory {

    @Value("${mysession.user}")
    private String USER_SESSION;

    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER_PATH;

    public String getUserSession(){
        return USER_SESSION;
    }

    public String getUploadFolderPath(){
        return UPLOADED_FOLDER_PATH;
    }

}
