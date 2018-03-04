<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>当前已注册用户</title>
</head>
<body>
<h1>当前已注册用户</h1>
<table width="100%">
    <tr>
        <td>id</td>
        <td>username</td>
        <td>password</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.uid}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
