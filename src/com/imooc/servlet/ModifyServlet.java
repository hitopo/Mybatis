package com.imooc.servlet;

import com.imooc.bean.Command;
import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 显示修改页面，传递参数 */
public class ModifyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //解决中文乱码问题
        request.setCharacterEncoding("utf-8");

        //获取前台数据
        String id = request.getParameter("id");

        //调用service获取数据
        QueryService queryService = new QueryService();
        Command command = queryService.queryOneById(id);

        //保存数据
        request.setAttribute("command",command);
        //页面跳转
        request.getRequestDispatcher("/WEB-INF/jsp/back/modify.jsp").forward(request, response);
    }
}
