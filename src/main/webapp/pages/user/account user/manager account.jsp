<%@ page import="by.academy.project.hotel.entities.user.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.06.2023
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manager account</title>
    <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<p>Hello, <%= ((User) session.getAttribute("user")).getName() %>! Welcome to your account</p>
<a href="/user/read" class="c">My details</a>
<a href="/pages/user/search user/user search.jsp" class="c">Search guest</a>
<a href="/pages/user/create user/user creation by manager.jsp" class="c">Create guest</a>
<form action="/users/read" method="post">
    <a href="/users/read" class="c">All guests</a>
</form>
<a href="/rooms/read" class="c">All rooms</a>
</body>
</html>
