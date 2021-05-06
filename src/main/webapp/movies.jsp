<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 86180
  Date: 2021/5/5
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies</title>
</head>
<body>
<c:if test="${userSession == null}">
    <a href="login.jsp">登录</a>
    <a href="register.jsp">注册</a>
</c:if>
<c:if test="${userSession != null}">
    <span>hello, ${userSession.username}</span> <a href="${pageContext.request.contextPath}/logout.do">注销</a>
</c:if>
<form action="${pageContext.request.contextPath}/getMovieByName.do">
    <input type="hidden" name="method" value="getMovieByName">
    <input type="text" name="name" placeholder="输入电影名">
    <input type="submit" value="搜索">
</form>
<c:forEach var="movie" items="${movieList}">
    <img src="media/upload/${movie.id}/${movie.id}.jpeg">
    <div>
        <span>${movie.name}</span>
        <span>${movie.director}</span>
        <span>${movie.location}</span>
        <span>${movie.actor}</span>
        <span>${movie.filmedTime}</span>
    </div>
    <hr>
</c:forEach>
</body>
</html>
