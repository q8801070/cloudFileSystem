package com.myweb.service.impl;

import com.myweb.dao.UserFilesMapper;
import com.myweb.model.AutoSortFileModel;
import com.myweb.pojo.User;
import com.myweb.pojo.UserFiles;
import com.myweb.service.AutoSortFileService;
import com.myweb.utils.ConfigurationFactory;
import com.myweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class AutoSrotFileServiceImpl implements AutoSortFileService {

    @Autowired
    ConfigurationFactory configurationFactory;

    @Autowired
    UserFilesMapper userFilesMapper;

    //回傳自動檔案分類頁面的數據表格
    @Override
    public AutoSortFileModel getTable(int id,int type) {
        AutoSortFileModel autoSortFileModel = new AutoSortFileModel(); //對應前端表格的model
        ArrayList<HashMap<String,Object>> data = new ArrayList<>(); //數據表格的data

        ArrayList<UserFiles> userFiles; //讀取資料庫的檔案資訊

        //找到指定id使用者之檔案總類對應的所有檔案
        if(type == 6){
            //如果是找所有類型檔案
            userFiles = userFilesMapper.selectAllFilesById(id);
        }else{
            //如果是找單一類型檔案
            userFiles = userFilesMapper.selectFilesByIdAndType(id,type);
        }

        //製作數據表格資料
        for (UserFiles userFile : userFiles) {
            HashMap<String,Object> map = new HashMap<>(); //數據表格data內的單筆資料
            map.put("imagePath",getImagePathByType(userFile.getType()));
            map.put("fileName",userFile.getFileName());
            map.put("postfix",userFile.getPostfix());


            //檔案大小轉換成中文
            if(userFile.getSize() <1024){
                map.put("size",(float)(userFile.getSize()) + "Bytes");

            } else if(userFile.getSize() >= 1024 && userFile.getSize() < (1024*1024)){
                map.put("size",(float)((float)userFile.getSize() / 1024) + "KB");

            }else{
                map.put("size",(float)((float)userFile.getSize() / 1024 / 1024) + "MB");
            }

            map.put("fileType",getTypeNameByType(userFile.getType()));
            map.put("downloadTime",userFile.getDownloadTime());

            data.add(map);
        }

        autoSortFileModel.setCode(0);
        autoSortFileModel.setCount(userFiles.size()); //看資料數
        autoSortFileModel.setData(data);
        autoSortFileModel.setMsg("");

        return autoSortFileModel;
    }

    //檔案下載
    @Override
    public boolean downloadFile(HttpServletRequest request,
                                HttpServletResponse response,
                                String fileName) {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(configurationFactory.getUserSession());

        //避免有人透過目錄路徑查其他資料夾的檔案
        if(fileName.contains("/")){
            return false;
        }

        File file = new File(configurationFactory.getUploadFolderPath() +'/'+ user.getId() + "/" +fileName);
        if(!file.exists()){
            return false;
        }

        response.reset();
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName );

        try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file))) {

            byte[] buff = new byte[1024];
            OutputStream os = response.getOutputStream();
            int i = 0;

            while ((i = bis.read(buff)) != -1) {
                os.write(buff, 0, i);
            }

            os.flush();

        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    //根據檔案總類回傳對應圖片路徑
    private String getImagePathByType(int type){
        switch (type){
            case FileUtil.DOCUMENT:
                return "/static/images/document.png";
            case FileUtil.IMAGE:
                return "/static/images/image.png";
            case FileUtil.VIDEO:
                return "/static/images/video.png";
            case FileUtil.MUSIC:
                return "static/images/music.png";
            case FileUtil.OTHER:
                return "/static/images/other.png";
        }

        return "/static/images/cloud.png";
    }

    private String getTypeNameByType(int type){
        switch (type){
            case FileUtil.DOCUMENT:
                return "文檔";
            case FileUtil.IMAGE:
                return "圖片";
            case FileUtil.VIDEO:
                return "影片";
            case FileUtil.MUSIC:
                return "音樂";
            case FileUtil.OTHER:
                return "其他類型";
        }

        return "其他類型";
    }
}



















