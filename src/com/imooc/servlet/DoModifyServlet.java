package com.imooc.servlet;

import com.imooc.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 完成修改操作
 */
public class DoModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码问题
        request.setCharacterEncoding("utf-8");

        //接收表单数据
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String[] contents = request.getParameterValues("content");

        //调用service层更新指令
        MaintainService maintainService = new MaintainService();
        maintainService.modifyOne(id,name,description,contents);

        //页面跳转
        response.sendRedirect("List.action");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
