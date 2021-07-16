<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>

    <script>
        function userFunc() {
            $.ajax( {
                url:"${pageContext.request.contextPath}/user/look",
                data: {"password":$("#password").val()},
                success: function (data) {
                    console.log(data.toString());
                    $("#pwd-info").html(data);
                }
            })
        }
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/check" name="login-form">
    用户ID:<input type="text" name="uid">
    密码: <input type="text" id="password" onblur="userFunc()">
    <span id="pwd-info"></span>
    <input type="submit" value="提交">
</form>

</body>
</html>
