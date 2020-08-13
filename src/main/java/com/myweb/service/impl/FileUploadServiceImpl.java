package com.myweb.service.impl;

import com.myweb.dao.UserFilesMapper;
import com.myweb.dao.UserMapper;
import com.myweb.dao.UserStoreMapper;
import com.myweb.pojo.UserFiles;
import com.myweb.pojo.UserStore;
import com.myweb.service.FileUploadService;
import com.myweb.utils.FileUtil;
import com.myweb.utils.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import com.myweb.pojo.User;


@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserFilesMapper userFilesMapper;

    @Autowired
    UserStoreMapper userStoreMapper;

    @Override
    public boolean fileUpload(MultipartFile[] uploadfiles, HttpServletRequest request) {

        //取得檔案名稱
        String uploadedFileName = Arrays.stream(
                uploadfiles).map(x -> x.getOriginalFilename())
                .filter(x -> !StringUtils.isEmpty(x))
                .collect(Collectors.joining(","));

        //如果沒收到檔案
        if(StringUtils.isEmpty(uploadedFileName)){
            return false;
        }

        try {
            //儲存檔案
            boolean result = saveUploadedFiles(Arrays.asList(uploadfiles), request);
            return result; //如果儲存成功

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //儲存從客戶端傳來的檔案
    private boolean saveUploadedFiles(List<MultipartFile> files,HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(sessionFactory.getUserSession());


        for (MultipartFile file : files) {

            //檢查檔案室否為空
            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }

            //檢查容量
            UserStore userStore = userStoreMapper.selectById(user.getId());
            if(userStore.getCurrent_size() + file.getBytes().length > userStore.getMax_size()){
                //如果該檔案超出容量限制
                continue; //繼續下一個檔案
            }

            byte[] bytes = file.getBytes();
            //根據id儲存至使用者專屬資料夾
            Path path = Paths.get(UPLOADED_FOLDER + "/" + user.getId() + "/" + file.getOriginalFilename());

            //取得檔案大小
            int fileSize = bytes.length;

            //取得副檔名
            String[] split = file.getOriginalFilename().split("\\.");
            String postfix = "." + split;

            //紀錄檔案資訊
            UserFiles userFiles = new UserFiles();
            userFiles.setId(user.getId());
            userFiles.setFile_name(file.getOriginalFilename());
            userFiles.setType(fileUtil.getFileType(postfix));
            userFiles.setDownload_time(0);
            userFiles.setSize(fileSize);

            userFilesMapper.insert(userFiles); //加入該檔案資訊到資料庫

            //增加資料庫硬碟當前使用量


            Files.write(path, bytes);
        }

        return true;

    }


}


















