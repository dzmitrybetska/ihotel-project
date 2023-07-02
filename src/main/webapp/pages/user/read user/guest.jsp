<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.06.2023
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Guest data</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>login</th>
        <th>password</th>
        <th>email</th>
        <th>phone</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${userDto.name}</td>
        <td>${userDto.surname}</td>
        <td>${userDto.login}</td>
        <td>${userDto.password}</td>
        <td>${userDto.email}</td>
        <td>${userDto.phone}</td>
    </tr>
    </tbody>
</table>
<a href="/pages/user/update%20user/guest%20himself%20update.jsp" class="c">Change my details</a>
</body>
</html>
