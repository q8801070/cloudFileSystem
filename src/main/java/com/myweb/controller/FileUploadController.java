package com.myweb.controller;


import com.myweb.service.FileUploadService;
import com.myweb.utils.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;


    //使用者上傳檔案
    @PostMapping("/upload")
    public ResponseMessage upload(@RequestParam("file")MultipartFile[] uploadfiles, HttpServletRequest request){

        boolean result = fileUploadService.fileUpload(uploadfiles,request); //上傳到指定使用者資料夾內

        if(result == true){
            return ResponseMessage.success();
        }else{
            return ResponseMessage.error();
        }

    }




}





















