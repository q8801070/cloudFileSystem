package com.myweb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * Session管理工廠類
 */
@Component
public class SessionFactory {

    @Value("${mysession.user}")
    private String USER_SESSION;

    public String getUserSession(){
        return USER_SESSION;
    }

}
