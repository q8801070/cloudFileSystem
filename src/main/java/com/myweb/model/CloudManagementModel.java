package com.myweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 雲端管理頁面基本數據接收與更新
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CloudManagementModel {

    private String username;
    private int myDocumentCount;
    private int myImageCount;
    private int myVideoCount;
    private int myMusicCount;
    private int otherDocumentCount;

}













