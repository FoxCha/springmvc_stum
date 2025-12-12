<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>课程表单</title>
</head>
<body>
<h2>课程信息表单</h2>
<c:set var="formAction" value="${course.id == null ? '/courses' : '/courses/'.concat(course.id)}"/>
<c:if test="${not empty error}">
    <p style="color: red">${error}</p>
</c:if>
<form method="post" action="${formAction}">
    <div>
        <label>课程名（50字以内）：</label>
        <input type="text" name="name" maxlength="50" value="${course.name}" required>
    </div>
    <div>
        <label>课程描述（200字以内）：</label>
        <textarea name="description" maxlength="200">${course.description}</textarea>
    </div>
    <div>
        <label>授课时间（格式：2021-10-01）：</label>
        <input type="text" name="sessionTime" value="${course.sessionDateFormatted}" required>
    </div>
    <div>
        <label>可选人数（1-150）：</label>
        <input type="number" name="capacity" min="1" max="150" value="${course.capacity}" required>
    </div>
    <div>
        <label>教室：</label>
        <select name="classroom" required>
            <c:forEach items="${classrooms}" var="room">
                <option value="${room}" ${course.classroom == room ? 'selected' : ''}>${room}</option>
            </c:forEach>
        </select>
    </div>
    <div>
        <label>可选专业：</label>
        <c:forEach items="${majorOptions}" var="major">
            <label>
                <input type="checkbox" name="majors" value="${major}" <c:if test="${course.majors != null && course.majors.contains(major)}">checked</c:if>>
                ${major}
            </label>
        </c:forEach>
    </div>
    <div>
        <label>授课方式：</label>
        <label><input type="radio" name="deliveryMethod" value="线下" ${course.deliveryMethod == '线下' ? 'checked' : ''} required>线下</label>
        <label><input type="radio" name="deliveryMethod" value="线上" ${course.deliveryMethod == '线上' ? 'checked' : ''}>线上</label>
    </div>
    <button type="submit">提交</button>
</form>
<div>
    <a href="/courses">返回课程列表</a> | <a href="/home">返回首页</a>
</div>
</body>
</html>
