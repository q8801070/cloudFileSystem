package com.myweb.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
//使用者的檔案倉庫資訊
public class UserStore implements Serializable {

    private int id;
    private int store_id;
    private int current_size;
    private int max_size;

}
















