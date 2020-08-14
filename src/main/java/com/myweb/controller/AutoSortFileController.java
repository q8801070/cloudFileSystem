package com.myweb.controller;


import com.myweb.model.AutoSortFileModel;
import com.myweb.service.AutoSortFileService;
import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myweb.pojo.User;

import java.io.*;

@RestController
@RequestMapping("/autoSortFile")
public class AutoSortFileController {

    @Value("${mycloud.upload-folder}")
    private String UPLOAD_FOLDER;

    @Autowired
    ConfigurationFactory configurationFactory;

    @Autowired
    AutoSortFileService autoSortFileService;

    //將對應數據傳給前端做表格渲染
    @RequestMapping("/reloadTable/{fileType}")
    public AutoSortFileModel myDocumentModel(HttpServletRequest request,@PathVariable("fileType")int type){

        System.out.println("here");

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(configurationFactory.getUserSession());

        AutoSortFileModel autoSortFileModel = autoSortFileService.getTable(user.getId(), type);

        return autoSortFileModel;
    }

    @RequestMapping("/download/{fileName}")
    public String download(@PathVariable("fileName")String fileName,
                           HttpServletResponse response,
                           HttpServletRequest request) throws IOException {


    }

}














