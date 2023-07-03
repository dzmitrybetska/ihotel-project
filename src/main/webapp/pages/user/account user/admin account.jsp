<%@ page import="by.academy.project.hotel.entities.user.User" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.06.2023
  Time: 04:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin account</title>
    <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<p>Hello, <%= ((User) session.getAttribute("user")).getName() %>! Welcome to your account</p>
<a href="/user/read" class="c">My details</a>
<a href="/pages/user/search user/user search.jsp" class="c">Search user</a>
<a href="/pages/user/create user/user creation by admin.jsp" class="c">Create user</a>
<a href="/pages/user/delete user/deleting a user.jsp" class="c">Delete user</a>
<form action="/users/read" method="post">
    <a href="/users/read" class="c">All users</a>
</form>
<a href="/pages/room/create room/room create.jsp" class="c">Create room</a>
<a href="/pages/room/update room/room update.jsp" class="c">Update room</a>
<a href="/pages/room/delete room/delete%20room.jsp" class="c">Delete room</a>
<a href="/rooms/read" class="c">All rooms</a>
<form action="/exit" method="post">
    <a href="/exit" class="c">Exit</a>
</form>
</body>
</html>
