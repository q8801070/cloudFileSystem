package com.myweb.service.impl;

import com.myweb.dao.UserFilesMapper;
import com.myweb.dao.UserMapper;
import com.myweb.dao.UserStoreMapper;
import com.myweb.pojo.UserFiles;
import com.myweb.pojo.UserStore;
import com.myweb.service.FileUploadService;
import com.myweb.utils.FileUtil;
import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @Autowired
    ConfigurationFactory configurationFactory;

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
        User user = (User) session.getAttribute(configurationFactory.getUserSession());


        for (MultipartFile file : files) {

            //檢查檔案室否為空
            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }

            //檢查容量
            UserStore userStore = userStoreMapper.selectById(user.getId());
            if(userStore.getCurrentSize() + file.getBytes().length > userStore.getMaxSize()){
                //如果該檔案超出容量限制
                continue; //繼續下一個檔案
            }

            String thisFileName = file.getOriginalFilename(); //取得檔案名稱

            byte[] bytes = file.getBytes();
            //根據id儲存至使用者專屬資料夾
            if(fileUtil.isFileExist(user.getId(),thisFileName)){
                //如果該檔案名稱之檔案已經存在，通過時間戳重組檔案名
                String[] nameArray = thisFileName.split("\\.");

                thisFileName = nameArray[0] + System.currentTimeMillis() + "." + nameArray[1];
                System.out.println(thisFileName);
            }
            Path path = Paths.get(configurationFactory.getUploadFolderPath() + "/" + user.getId() + "/" + thisFileName);

            //取得檔案大小
            int fileSize = bytes.length;

            //取得副檔名
            String[] split = thisFileName.split("\\.");
            String postfix = "." + split[1];

            //紀錄檔案資訊
            UserFiles userFiles = new UserFiles();
            userFiles.setId(user.getId());
            userFiles.setFileName(thisFileName);
            userFiles.setType(fileUtil.getFileType(postfix));
            userFiles.setDownloadTime(0);
            userFiles.setSize(fileSize);
            userFiles.setPostfix(postfix);

            userFilesMapper.insert(userFiles); //加入該檔案資訊到資料庫

            //取得並更新當前檔案容量
            userStore.setCurrentSize(fileUtil.getUserFileCurrentSize(userStore.getId(),FileUtil.ALL));
            userStoreMapper.updateById(userStore);

            Files.write(path, bytes);
        }

        return true;

    }


}


















