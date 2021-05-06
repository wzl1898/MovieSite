<%--
  Created by IntelliJ IDEA.
  User: 86180
  Date: 2021/5/6
  Time: 1:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/updateMovie.do">
    <input type="hidden" name="method" value="updateMovie">
    id:<input type="text" name="id" value="${id}">
    name:<input type="text" name="name" value="${name}">
    director:<input type="text" name="director" value="${director}">
    location:<input type="text" name="location" value="${location}">
    actor:<input type="text" name="actor" value="${actor}">
    filmedTime:<input type="text" name="filmedTime" value="${filmedTime}">
    <input type="submit" value="修改">
</form>
</body>
</html>
