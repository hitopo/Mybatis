package com.imooc.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 初始化talk.jsp页面的servlet
 */
public class InitTalkServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式，避免中文乱码
        request.setCharacterEncoding("utf-8");

        //页面跳转
        request.getRequestDispatcher("/WEB-INF/jsp/front/talk.jsp").forward(request, response);
    }
}
