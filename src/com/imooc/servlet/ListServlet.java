package com.imooc.servlet;

import com.imooc.bean.Command;
import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式，避免中文乱码
        request.setCharacterEncoding("utf-8");

        //获取界面参数
        String command = request.getParameter("command");
        String description = request.getParameter("description");

        //调用service层处理数据
        QueryService queryService = new QueryService();
        List<Command> commandList = queryService.queryCommandList(command,description);

        //返回数据给jsp页面
        request.setAttribute("commandList",commandList);
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request,response);
    }
}
