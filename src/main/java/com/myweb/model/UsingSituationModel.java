package com.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 封裝以傳遞使用情況頁面資訊
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsingSituationModel implements Serializable {

    private int leftSize;
    private int useSize;

    private int fileCount;

    private int documentCount;
    private int imageCount;
    private int videoCount;
    private int musicCount;
    private int otherCount;


}
