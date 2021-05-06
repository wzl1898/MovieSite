<%--
  Created by IntelliJ IDEA.
  User: 86180
  Date: 2021/5/6
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/admin/addMovie.do" enctype="multipart/form-data" method="post">
    <input type="hidden" name="method" value="addMovie">
    id:<input type="text" name="id" placeholder="id">
    name:<input type="text" name="name" placeholder="name">
    director:<input type="text" name="director" placeholder="director">
    location:<input type="text" name="location" placeholder="location">
    actor:<input type="text" name="actor" placeholder="actor">
    filmedTime:<input type="text" name="filmedTime" placeholder="filmedTime">
    image:<input name="img" type="file"/>
    <input type="submit" value="提交">
</form>
</body>
</html>
