package com.myweb.service.impl;

import com.myweb.dao.UserMapper;
import com.myweb.model.CloudManagementModel;
import com.myweb.pojo.User;
import com.myweb.service.CloudManagementService;
import com.myweb.utils.FileUtil;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CloudManagementServiceImpl implements CloudManagementService {

    @Autowired
    FileUtil fileUtil;

    @Autowired
    UserMapper userMapper;

    @Override
    public CloudManagementModel refreshPage(int id) {

        CloudManagementModel cloudManagementModel = new CloudManagementModel();

        User user = userMapper.selectById(id);
        System.out.println("refreshPage>" + user);

        cloudManagementModel.setUserName(user.getUsername());
        cloudManagementModel.setMyDocumentCount(fileUtil.getUserFileCount(user.getId(),FileUtil.DOCUMENT));
        cloudManagementModel.setMyImageCount(fileUtil.getUserFileCount(user.getId(),FileUtil.IMAGE));
        cloudManagementModel.setMyMusicCount(fileUtil.getUserFileCount(user.getId(),FileUtil.MUSIC));
        cloudManagementModel.setMyVideoCount(fileUtil.getUserFileCount(user.getId(),FileUtil.VIDEO));
        cloudManagementModel.setOtherDocumentCount(fileUtil.getUserFileCount(user.getId(),FileUtil.OTHER));

        return cloudManagementModel;

    }
}

