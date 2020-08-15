package com.myweb.controller;


import com.myweb.model.CloudManagementModel;
import com.myweb.service.CloudManagementService;
import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.myweb.pojo.User;

@RestController
@RequestMapping("/cloudManagement")
public class CloudManagementController {

    @Autowired
    ConfigurationFactory configurationFactory;

    @Autowired
    CloudManagementService cloudManagementService;

    @PostMapping("/refreshPage")
    public CloudManagementModel refreshPage(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(configurationFactory.getUserSession());

        CloudManagementModel cloudManagementModel = cloudManagementService.refreshPage(user.getId());

        return cloudManagementModel;

    }

}












