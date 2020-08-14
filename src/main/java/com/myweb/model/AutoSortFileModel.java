package com.myweb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * 自動分類檔案各頁面數據表格，所須的資料封裝。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoSortFileModel implements Serializable {

    private int code;
    private String msg;
    private int count;
    private ArrayList<HashMap<String,Object>> data = new ArrayList<>();

//    private String imagePath;
//    private String fileName;
//    private String postfix;
//    private String size;
//    private String downloadTime;
//    private String type;

}
















