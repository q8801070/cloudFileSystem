package com.myweb.service;


import com.myweb.model.UsingSituationModel;

public interface UsingSituationService {

    //根據user id回傳網站頁面的相關資訊。
    public UsingSituationModel refreshPage(int id);

}
