package com.myweb.service;

import com.myweb.model.AutoSortFileModel;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface AutoSortFileService {

    //回傳資料給前端自動分類頁面下的表格
    public AutoSortFileModel getTable(int id,int type);

    //檔案下載
    public boolean downloadFile(HttpServletRequest request,
                                HttpServletResponse response,
                                String fileName);
}
