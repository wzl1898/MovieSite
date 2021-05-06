<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login.do" method="post">
    <div class="info">${error}</div>
    <div class="inputbox">
        <label>username:</label>
        <input type="text" name="username" placeholder="input username" required>
    </div>
    <div class="inputbox">
        <label>password:</label>
        <input type="password" name="password" placeholder="input password" required>
    </div>
    <div class="subBtn">
        <input type="submit" value="login"> <a href="register.jsp"> 注册</a>
    </div>
</form>
</body>
</html>