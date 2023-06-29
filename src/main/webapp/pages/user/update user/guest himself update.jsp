<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.06.2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Guest update</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>new name</th>
        <th>new surname</th>
        <th>new login</th>
        <th>new password</th>
        <th>new email</th>
        <th>new phone</th>
    </tr>
    </thead>

    <tbody>
    <form action="/user/update" method="post">
        <tr>
            <td><input type="text" name="name" value="${user.name}"></td>
            <td><input type="text" name="surname" value="${user.surname}"></td>
            <td><input type="text" name="login" value="${user.login}"></td>
            <td><input type="text" name="password" value="${user.password}"></td>
            <td><input type="text" name="email" value="${user.email}"></td>
            <td><input type="text" name="phone" value="${user.phone}"></td>
            <td><input type="submit" value="UPDATE"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
