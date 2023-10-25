package servlet;

import beans.UserDataList;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet(name = "ClearTable", value = "/v1/cleartable")
public class ClearTableServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String address = getServletContext().getContextPath();
        ServletContext context = getServletContext();
        context.setAttribute("UserDataList", new UserDataList());
        response.sendRedirect(address);
    }
}
