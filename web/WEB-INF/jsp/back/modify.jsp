<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<%
    String path = request.getContextPath();
    // 获得项目完全路径（假设你的项目叫MyApp，那么获得到的地址就是http://localhost:8080/MyApp/）:
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<head>
    <title>修改指令内容</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/css/modify.css">
    <script type="text/javascript" src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/back/modify.js"></script>
</head>
<body>
<form action="<%=basePath%>DoModify.action" method="post" id="mainForm">
    <%--埋一个hidden表单，将修改的commandId传递过去--%>
    <input type="hidden" name="id" value="${command.id}">
    <div class="header" style="color:#6E6E6E;">
        当前位置：&gt;内容管理&gt;修改内容
    </div>
    <div class="content">
        <label for="name">指令名称：</label>
        <input type="text" name="name" id="name" value="${command.name}">
        <br>
        <label for="description">描&nbsp;&nbsp;&nbsp;&nbsp;述：</label>
        <input type="text" name="description" id="description" value="${command.description}">
        <br>
        <strong>内&nbsp;容：</strong>
        <%--${command.contentList[0].content--%>
        <a href="#" id="AddMoreFileBox" class="btn_addMore">添加更多内容</a>
        <div id="InputsWrapper">
            <c:forEach items="${command.contentList}" var="commandcontent">
            <div>
                <textarea name="content">${commandcontent.content}</textarea>
                <a href="#" class="removeclass">×</a>
            </div>
            </c:forEach>
        </div>
        <br>
        <input type="button" name="submit-btn" value="确认修改" id="submit-btn">
    </div>
</form>
</body>
</html>
