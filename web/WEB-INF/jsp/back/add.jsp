<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <title>添加新的内容</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/add.css">
    <script type="text/javascript" src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/back/add.js"></script>
</head>
<body>
<form action="<%=basePath%>DoAdd.action" method="post" id="mainForm">
    <div class="header" style="color:#6E6E6E;">
        当前位置：&gt;内容管理&gt;添加内容
    </div>
    <div class="content">
        <label for="name">指令名称：</label>
        <input type="text" name="name" id="name">
        <br>
        <label for="description">描&nbsp;&nbsp;&nbsp;&nbsp;述：</label>
        <input type="text" name="description" id="description">
        <br>
        <strong>内&nbsp;容：</strong>
        <a href="#" id="AddMoreFileBox" class="btn_addMore">添加更多内容</a>
        <div id="InputsWrapper">
            <div>
                <textarea name="content" id="field_1"></textarea>
                <a href="#" class="removeclass">×</a>
            </div>
        </div>
        <input type="button" name="submit-btn" value="添加指令" id="submit-btn">
    </div>
</form>
</body>
</html>
