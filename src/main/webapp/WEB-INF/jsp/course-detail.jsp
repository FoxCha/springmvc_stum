<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程详情</title>
</head>
<body>
<h2>${course.name}</h2>
<p><strong>课程描述：</strong> ${course.description}</p>
<p><strong>授课时间：</strong> ${course.sessionDateFormatted}（毫秒：${course.sessionTimeMillis}）</p>
<p><strong>可选人数：</strong> ${course.capacity}</p>
<p><strong>教室：</strong> ${course.classroom}</p>
<p><strong>可选专业：</strong> ${majors}</p>
<p><strong>授课方式：</strong> ${course.deliveryMethod}</p>
<div>
    <a href="/courses/${course.id}/edit">编辑课程</a> | <a href="/courses">返回列表</a> | <a href="/home">返回首页</a>
</div>
</body>
</html>
