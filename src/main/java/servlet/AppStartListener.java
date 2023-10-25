package servlet;

import beans.UserDataList;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

//@WebListener(value = "/listener")
public class AppStartListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce){
        ServletContext context = sce.getServletContext();
        UserDataList list = new UserDataList();
        context.setAttribute("UserDataList", list);
    }
}
