package com.imooc.servlet;

import com.imooc.bean.Command;
import com.imooc.entity.Page;
import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

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
        //接受当前页码
        String currentPage = request.getParameter("currentPage");
        //创建分页对象
        Page page = new Page();
        //正则表达式，匹配数字
        Pattern pattern = Pattern.compile("[0-9]{1,9}");
        if(currentPage==null||!pattern.matcher(currentPage).matches()){
            //传递的参数是空或者不是数字就打开第一页
            page.setCurrentPage(1);
        } else {
            page.setCurrentPage(Integer.parseInt(currentPage));
        }

        //调用service层处理数据
        QueryService queryService = new QueryService();
        List<Command> commandList = queryService.queryCommandList(command,description,page);

        //传递数据
        request.setAttribute("command",command);
        request.setAttribute("description",description);
        request.setAttribute("commandList",commandList);
        request.setAttribute("page",page);

        //页面跳转
        request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request,response);
    }
}
