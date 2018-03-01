<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta charset="UTF-8">
    <style>
        * {
            text-align: center;
            text-decoration: none;
        }
    </style>
</head>
<body>
<h1>登录页面</h1>
${msg}
<form action="/login" method="post">
    账号：<input type="text" name="username" />
    <br />
    密码：<input type="text" name="password" />
    <br />
    <input type="submit"/>
    <br>
    <a href="register.jsp">注册页面</a>
</form>
</body>
</html>
