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
    <title>Admin</title>
</head>
<body>
<span>hello, admin</span>  <a href="${pageContext.request.contextPath}/logout.do">注销</a>
<a href="addMovie.jsp">添加电影</a>

<form action="${pageContext.request.contextPath}/getMovieByName.do">
    <input type="hidden" name="method" value="getMovieByName">
    <input type="text" name="name" placeholder="输入电影名">
    <input type="submit" value="搜索">
</form>

<c:forEach var="movie" items="${movieList}">
    <div>
        <img src="media/upload/${movie.id}/${movie.id}.jpeg">
        <div><span>${movie.name}</span>
            <span>${movie.director}</span>
            <span>${movie.location}</span>
            <span>${movie.actor}</span>
            <span>${movie.filmedTime}</span>
        </div>
        <form action="${pageContext.request.contextPath}/admin/deleteMovie.do">
            <input type="hidden" name="method" value="deleteMovie">
            <input type="hidden" name="id" value="${movie.id}">
            <input type="submit" value="删除">
        </form>
        <form action="${pageContext.request.contextPath}/admin/updateMovieForm">
            <input type="hidden" name="method" value="deleteMovie">
            <input type="hidden" name="id" value="${movie.id}">
            <input type="hidden" name="name" value="${movie.name}">
            <input type="hidden" name="director" value="${movie.director}">
            <input type="hidden" name="location" value="${movie.location}">
            <input type="hidden" name="actor" value="${movie.actor}">
            <input type="hidden" name="filmedTime" value="${movie.filmedTime}">
            <input type="submit" value="修改">
        </form>
        <hr>
    </div>
</c:forEach>
</body>
</html>
