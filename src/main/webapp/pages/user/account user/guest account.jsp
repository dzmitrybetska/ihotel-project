<%@ page import="by.academy.project.hotel.entities.user.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.06.2023
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guest account</title>
    <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<p>Hello, <%= ((User) session.getAttribute("user")).getName() %>! Welcome to your account!</p>
<a href="/user/read" class="c">My details</a>
<a href="/rooms/read" class="c">All rooms</a>
</body>
</html>
