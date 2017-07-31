package com.imooc.servlet;

import com.imooc.service.MaintainService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteOneServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式，避免中文乱码
        request.setCharacterEncoding("utf-8");

        //获取界面参数
        String id = request.getParameter("id");

        //调用service层处理数据
        MaintainService maintainService = new MaintainService();
        maintainService.deleteOne(id);

        //返回数据给jsp页面
        request.getRequestDispatcher("/List.action").forward(request,response);
    }
}
