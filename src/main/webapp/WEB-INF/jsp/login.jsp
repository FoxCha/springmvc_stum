<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h2>课程管理系统 - 登录</h2>
<form method="post" action="/login">
    <div>
        <label>用户名：</label>
        <input type="text" name="username" required>
    </div>
    <div>
        <label>密码：</label>
        <input type="password" name="password" required>
    </div>
    <button type="submit">登录</button>
</form>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
</body>
</html>
