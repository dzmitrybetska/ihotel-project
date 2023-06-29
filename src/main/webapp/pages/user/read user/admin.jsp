<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2023
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Admin data</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>surname</th>
        <th>login</th>
        <th>password</th>
        <th>passport</th>
        <th>email</th>
        <th>phone</th>
        <th>role</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${userDto.id}</td>
        <td>${userDto.name}</td>
        <td>${userDto.surname}</td>
        <td>${userDto.login}</td>
        <td>${userDto.password}</td>
        <td>${userDto.passport}</td>
        <td>${userDto.email}</td>
        <td>${userDto.phone}</td>
        <td>${userDto.role}</td>
    </tr>
    </tbody>
</table>
<a href="/pages/user/update user/updating user by admin.jsp" class="c">Change my details</a>
</body>
</html>
