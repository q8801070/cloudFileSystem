package com.myweb.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


/**
 * 對應資料庫user_store表
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
//使用者的檔案倉庫資訊
public class UserStore implements Serializable {

    private Integer id;
    private Integer storeId;
    private Integer currentSize;
    private Integer maxSize;

}
















