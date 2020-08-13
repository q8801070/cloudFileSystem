package com.myweb.service.impl;

import com.myweb.dao.UserStoreMapper;
import com.myweb.model.UsingSituationModel;
import com.myweb.pojo.UserStore;
import com.myweb.service.UsingSituationService;
import com.myweb.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;


@Service
public class UsingSituationServiceImpl implements UsingSituationService {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserStoreMapper userStoreMapper;

    @Override
    public UsingSituationModel refreshPage(int id) {

        UsingSituationModel usingSituationModel = new UsingSituationModel();

        //檔案總數
        usingSituationModel.setFileCount(fileUtil.getUserFileCount(id,FileUtil.ALL));

        //各種文件總數
        usingSituationModel.setDocumentCount(fileUtil.getUserFileCount(id,FileUtil.DOCUMENT));
        usingSituationModel.setImageCount(fileUtil.getUserFileCount(id,FileUtil.IMAGE));
        usingSituationModel.setVideoCount(fileUtil.getUserFileCount(id,FileUtil.VIDEO));
        usingSituationModel.setMusicCount(fileUtil.getUserFileCount(id,FileUtil.MUSIC));
        usingSituationModel.setOtherCount(fileUtil.getUserFileCount(id,FileUtil.OTHER));


        //處理容量
        UserStore userStore = userStoreMapper.selectById(id); //根據名字是'id'的字段，而不是主鍵

        //更新剩餘容量
        userStore.setCurrentSize(fileUtil.getUserFileCurrentSize(id,FileUtil.ALL));
        userStoreMapper.updateById(userStore);

        //賦值給model
        usingSituationModel.setLeftSize(userStore.getMaxSize() - userStore.getCurrentSize()); //剩餘容量
        usingSituationModel.setUseSize(fileUtil.getUserFileCurrentSize(id,FileUtil.ALL)); //目前使用量

        return usingSituationModel;
    }

}

















