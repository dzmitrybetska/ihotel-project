<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2023
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Found user</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>passport</th>
        <th>email</th>
        <th>phone</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${foundUsers}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.surname}</td>
            <td>${user.passport}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/user/account" method="post">
    <td><input type="submit" value="My account"></td>
</form>
<a href="/pages/user/update user/updating user by manager.jsp" class="c">Edit guest</a>
</body>
</html>
