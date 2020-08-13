package com.myweb.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
//使用者持有的檔案資訊
public class UserFiles implements Serializable {

    private int id;
    private int file_id;
    private String file_name;
    private int size;
    private int type;
    private int download_time;
    private String postfix;



}
