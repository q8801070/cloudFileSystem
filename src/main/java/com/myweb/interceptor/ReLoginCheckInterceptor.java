package com.myweb.interceptor;

import com.myweb.utils.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.myweb.pojo.User;


//把尚未登錄的人趕到登錄頁面
public class ReLoginCheckInterceptor implements HandlerInterceptor {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute(sessionFactory.getUserSession());

        if(user == null){
            request.getRequestDispatcher("/templates/index.html").forward(request,response);
            return false;
        }else{
            return true;
        }


    }
}
