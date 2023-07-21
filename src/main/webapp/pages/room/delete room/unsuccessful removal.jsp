<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2023
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Unsuccessful deletion</title>
    <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<td>${error}</td>
<a href="/pages/room/delete room/delete room.jsp" class="c">Try again</a>
<form action="/user/account" method="post">
    <td><input type="submit" value="My account"></td>
</form>
</body>
</html>
