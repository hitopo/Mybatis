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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title>内容列表页面</title>
    <link href="<%=basePath%>resources/css/all.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=basePath%>resources/js/common/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/js/back/list.js"></script>
</head>
<body style="background: #e1e9eb;">
<form action="<%=basePath%>List.action" id="mainForm" method="post">
    <%--隐藏表单元素，用来提交当前页码--%>
    <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"/>

        <div class="right">
        <div class="current">当前位置：<a href="javascript:void(0)" style="color:#6E6E6E;">内容管理</a> &gt; 内容列表</div>
        <div class="rightCont">
            <p class="g_title fix">内容列表 <a class="btn03" href="<%=basePath%>Add.action">新 增</a>&nbsp;&nbsp;&nbsp;&nbsp;
                <%--注意在jsp中使用java脚本可以传递值--%>
                <a class="btn03" href="javascript:deleteBatch('<%=basePath %>');" onclick="return confirm('确定要删除吗？');">删 除</a></p>
            <table class="tab1">
                <tbody>
                <tr>
                    <td width="90" align="right">名称：</td>
                    <td>
                        <input type="text" class="allInput" value="${command}" name="command"/>
                    </td>
                    <td width="90" align="right">描述：</td>
                    <td>
                        <input type="text" class="allInput" value="${description}" name="description"/>
                    </td>
                    <td width="85" align="right"><input type="submit" class="tabSub" value="查 询"/></td>
                </tr>
                </tbody>
            </table>
            <div class="zixun fix">
                <table class="tab2" width="100%">
                    <tbody>
                    <tr>
                        <th><input type="checkbox" id="all" onclick="#"/></th>
                        <th>序号</th>
                        <th>名称</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${commandList}" var="Command" varStatus="status">
                        <tr <c:if test="${status.index %2 != 0}">style='background-color: #ECF6EE;'</c:if>>
                            <td><input type="checkbox" name="id" value="${Command.id}"/></td>
                            <td>${status.index + 1}</td>
                            <td>${Command.name}</td>
                            <td>${Command.description}</td>
                            <td>
                                <a href="<%=basePath%>Modify.action?id=${Command.id}">修改</a>&nbsp;&nbsp;&nbsp;
                                <a href="<%=basePath%>DeleteOne.action?id=${Command.id}" onclick="return confirm('确定要删除吗？');">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div class='page fix'>
                    共 <b>${page.totalNumber}</b> 条记录&nbsp;&nbsp;
                    <c:if test="${page.currentPage!=1}">
                    <a href="javascript:changeCurrentPage('1');" class='first'>首页</a>
                    <a href="javascript:changeCurrentPage('${page.currentPage-1}')" class='pre'>上一页</a>
                    </c:if>
                    当前第<span>${page.currentPage}/${page.totalPage}</span>页
                    <c:if test="${page.currentPage!=page.totalPage}">
                    <a href="javascript:changeCurrentPage('${page.currentPage+1}')" class='next'>下一页</a>
                    <a href="javascript:changeCurrentPage('${page.totalPage}')" class='last'>末页</a>
                    </c:if>
                    跳至&nbsp;<input id="toPageIndex" type='text' value="${page.currentPage}" class='allInput w28'/>&nbsp;页&nbsp;
                    <a href="javascript:changeCurrentPage($('#toPageIndex').val())" class='go'>GO</a>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>