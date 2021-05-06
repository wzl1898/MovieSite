<%--
  Created by IntelliJ IDEA.
  User: 86180
  Date: 2021/5/6
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register.do" method="post">
    username:<input type="text" name="username">
    password:<input type="text" name="password">
    id:<input type="text" name="id">
    <input type="submit" value="注册">
</form>
</body>
</html>
