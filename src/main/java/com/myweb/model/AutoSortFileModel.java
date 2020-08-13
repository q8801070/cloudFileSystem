package com.myweb.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 自動分類檔案各頁面數據表格，所須的資料封裝。
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoSortFileModel implements Serializable {

    private String imagePath;
    private String fileName;
    private String postfix;
    private String size;
    private String downloadTime;
    private String type;

}
















