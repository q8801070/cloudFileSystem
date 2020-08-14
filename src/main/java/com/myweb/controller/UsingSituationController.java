package com.myweb.controller;


import com.myweb.model.UsingSituationModel;
import com.myweb.service.UsingSituationService;
import com.myweb.utils.ConfigurationFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.myweb.pojo.User;

@RestController
@RequestMapping("/usingSituation")
public class UsingSituationController {

    @Autowired
    ConfigurationFactory configurationFactory;

    @Autowired
    UsingSituationService usingSituationService;


    //獲取頁面資訊以刷新
    @PostMapping("/refreshPage")
    @ResponseBody
    public UsingSituationModel refreshPage(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(configurationFactory.getUserSession());

        return usingSituationService.refreshPage(user.getId());
    }

}








