<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2023
  Time: 19:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Manager data</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>passport</th>
        <th>email</th>
        <th>phone</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${userDto.name}</td>
        <td>${userDto.surname}</td>
        <td>${userDto.passport}</td>
        <td>${userDto.email}</td>
        <td>${userDto.phone}</td>
    </tr>
    </tbody>
</table>
</body>
</html>
