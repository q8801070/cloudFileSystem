package com.myweb.utils;


import com.myweb.pojo.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 檔案相關功能工具類
 */
@Component
public class FileUtil {

    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER;
    public static final int DOCUMENT = 1; //表示文件類型
    public static final int IMAGE = 2;  //表示圖片類型
    public static final int VIDEO = 3; //表示影片類型
    public static final int MUSIC = 4; //表示音樂類型
    public static final int OTHER = 5; //表示其他類型
    public static final int ALL = 6; //表示所有類型


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

    //根據傳入的使用者id和命令，獲取其資料夾內，全部對應檔案類型所佔用的容量大小
    public int getUserFileCurrentSize(int id,int command){

        int totalsize = 0;

        //取得使用者資料夾路徑
        File path = new File(UPLOADED_FOLDER + "/" + id);

        //獲取路徑下所有檔案
        File[] files = path.listFiles();

        for (File file : files) {
            String name = file.getName();

            //取得副檔名
            String split = name.split("\\.")[1];
            String postfix = "." + split;
            int fileType = getFileType(postfix);
            if(command==6){
                totalsize += file.length();
            }else if(command == fileType){
                totalsize += file.length();
            }
        }

        return totalsize;

    }

    //根據傳入的使用者id和命令，獲取其資料夾內，指定種類的檔案數量
    public int getUserFileCount(int id,int command){

        int count = 0;

        //取得使用者資料夾路徑
        File path = new File(UPLOADED_FOLDER + "/" + id);

        //獲取路徑下所有檔案
        File[] files = path.listFiles();

        for (File file : files) {
            String name = file.getName();

            //取得副檔名
            String split = name.split("\\.")[1];
            String postfix = "." + split;
            int fileType = getFileType(postfix);
            if(command==6){
                count +=1;
            }else if(command == fileType){
                count +=1;
            }
        }

        return count;

    }


    //取得檔案類型 1文檔 2圖片 3影片 4音樂 5其他
    public int getFileType(String type){
        if (".chm".equals(type)||".txt".equals(type)||".xmind".equals(type)||".xlsx".equals(type)||".md".equals(type)
                ||".doc".equals(type)||".docx".equals(type)||".pptx".equals(type)
                ||".wps".equals(type)||".word".equals(type)||".html".equals(type)||".pdf".equals(type)){
            return  1;

        }else if (".bmp".equals(type)||".gif".equals(type)||".jpg".equals(type)||".ico".equals(type)||".vsd".equals(type)
                ||".pic".equals(type)||".png".equals(type)||".jepg".equals(type)||".jpeg".equals(type)||".webp".equals(type)
                ||".svg".equals(type)){
            return 2;

        } else if (".avi".equals(type)||".mov".equals(type)||".qt".equals(type)
                ||".asf".equals(type)||".rm".equals(type)||".navi".equals(type)||".wav".equals(type)
                ||".mp4".equals(type)||".mkv".equals(type)||".webm".equals(type)){
            return 3;

        } else if (".mp3".equals(type)||".wma".equals(type)){
            return 4;

        } else {
            return 5;
        }
    }

}














