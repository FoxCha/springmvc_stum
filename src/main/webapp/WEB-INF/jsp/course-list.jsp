<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程列表</title>
</head>
<body>
<h2>课程列表</h2>
<table border="1" cellpadding="8">
    <thead>
    <tr>
        <th>课程名</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${courses}" var="course">
        <tr>
            <td><a href="/courses/${course.id}">${course.name}</a></td>
            <td>
                <a href="/courses/${course.id}/edit">编辑</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <a href="/courses/new">新增课程</a> | <a href="/home">返回首页</a>
</div>
</body>
</html>
