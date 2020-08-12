package com.myweb.service.impl;

import com.myweb.service.FileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileUploadServiceImpl implements FileUploadService {

    @Value("${mycloud.upload-folder}")
    private String UPLOADED_FOLDER;

    @Override
    public boolean fileUpload(MultipartFile[] uploadfiles) {

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
            saveUploadedFiles(Arrays.asList(uploadfiles));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    //儲存從客戶端傳來的檔案
    private void saveUploadedFiles(List<MultipartFile> files) throws IOException {

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue; //繼續下一個檔案
            }

            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

        }

    }

}


















