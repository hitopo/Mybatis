package com.imooc.servlet;

import com.imooc.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 添加命令操作
 */
public class DoAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取前台数据
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] contents = request.getParameterValues("content");

        //调用service层完成添加操作
        MaintainService maintainService = new MaintainService();
        maintainService.addOne(name,description,contents);

        //路径跳转
        response.sendRedirect("List.action");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
