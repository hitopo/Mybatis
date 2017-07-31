<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>微信公众号</title>
    <style type="text/css">
    	a{
    		font-size: 20;
    		font-family: "微软雅黑";
    		line-height: 200%;
    		margin-left: 20px;
    		text-decoration: none;
    	}
    </style>
</head>

<body>
<h1>微信公众号自动回复功能的实现</h1>
<hr>
<a href="<%=basePath %>List.action">后台管理</a> <br>
<a href="<%=basePath %>InitTalk.action">前台页面</a>
</body>
</html>
