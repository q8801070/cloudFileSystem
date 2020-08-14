package com.myweb.controller;


import com.myweb.model.AutoSortFileModel;
import com.myweb.service.AutoSortFileService;
import com.myweb.utils.ConfigurationFactory;
import com.myweb.utils.ResponseMessage;
import org.apache.coyote.Response;
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

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(configurationFactory.getUserSession());

        AutoSortFileModel autoSortFileModel = autoSortFileService.getTable(user.getId(), type);

        return autoSortFileModel;
    }


    //檔案下載
    @RequestMapping("/download/{fileName}")
    public ResponseMessage download(@PathVariable("fileName")String fileName,
                                    HttpServletResponse response,
                                    HttpServletRequest request) {

        boolean result = autoSortFileService.downloadFile(request, response, fileName);

        if(result == true){
            return ResponseMessage.success();
        }else{
            return ResponseMessage.error();
        }
    }


    //檔案刪除
    @RequestMapping("/delete/{fileName}")
    public ResponseMessage delete(@PathVariable("fileName")String filename,
                                    HttpServletResponse response,
                                    HttpServletRequest request){
        System.out.println("here");

        boolean result = autoSortFileService.deleteFile(request, response, filename);


        if(result == true){
            return ResponseMessage.success();
        }else{
            return ResponseMessage.error();
        }

    }

}














