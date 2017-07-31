package com.imooc.servlet;

import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AutoReplyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理中文乱码问题
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        //设置返回http头信息
        response.setContentType("text/html,charset=utf-8");

        //获取输出流信息
        PrintWriter out = response.getWriter();

        //调用service获取返回数据
        QueryService queryService = new QueryService();
        out.write(queryService.queryByCommand(request.getParameter("content")));

        //关闭流
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
