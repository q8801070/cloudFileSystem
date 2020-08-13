package com.myweb.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface FileUploadService {

    //檔案上傳
    public boolean fileUpload(MultipartFile[] uploadfiles, HttpServletRequest request);

}
