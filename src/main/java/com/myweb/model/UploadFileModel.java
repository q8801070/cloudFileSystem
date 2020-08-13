package com.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 檔案上傳時的資料封裝
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileModel {

    private String extraField;
    private MultipartFile[] files;

}
