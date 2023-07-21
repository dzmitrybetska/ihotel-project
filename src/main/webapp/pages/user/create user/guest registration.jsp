<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2023
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration of a new user</title>
    <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>login (only latin letters, numbers and underscores, length must be between 5 and 20 characters)</th>
        <th>password (only Latin letters, numbers and underscores, at least 8 characters)</th>
        <th>email</th>
        <th>phone</th>
    </tr>
    </thead>
    <tbody>
    <form action="/user/create" method="post">
        <tr>
            <td><input type="text" name="name" placeholder="put your name"></td>
            <td><input type="text" name="surname" placeholder="put your surname"></td>
            <td><input type="text" name="login" placeholder="put your login"></td>
            <td><input type="text" name="password" placeholder="put your password"></td>
            <td><input type="text" name="email" placeholder="put your email"></td>
            <td><input type="text" name="phone" placeholder="put your phone"></td>
            <td><input type="submit" value="SEND"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
