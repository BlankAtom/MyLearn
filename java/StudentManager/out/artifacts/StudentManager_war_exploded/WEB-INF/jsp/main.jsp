<%--
  Created by IntelliJ IDEA.
  User: 12451
  Date: 2020/6/24
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <script src="${pageContext.request.contextPath}/static/js/jquery-3.5.1.js"></script>
    <script>
        $(function () {
            var data = $("#username");
            var html = "";
            if(data!=null){
                html = "<a href='${pageContext.request.contextPath}/user/logout'>注销</a>";
            }
            $("#logout-info").html(html);

        })
    </script>
</head>
<body>
<div>
    <h1><a href="#">首页</a></h1>
    <span>${username}</span>
    <h1><a href="#">登录</a> </h1>
    <p id="logout-info"> </p>
</div>

</body>
</html>
