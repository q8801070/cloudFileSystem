package com.myweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Session管理工廠類
 */
@Component
public class ConfigurationFactory {

    //使用者session名稱
    @Value("${mysession.user}")
    private String USER_SESSION;

    //檔案下載及上傳的根目錄路徑
    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER_PATH;

    //轉換至show-files.html頁面的cookie名稱
    @Value("${mycookie.show-file-page-number}")
    private String SHOW_FILE_PAGE_NUMBER;

    public String getUserSession(){
        return USER_SESSION;
    }

    public String getUploadFolderPath(){
        return UPLOADED_FOLDER_PATH;
    }

    public String getShowFilePageNumber(){
        return SHOW_FILE_PAGE_NUMBER;
    }

}
