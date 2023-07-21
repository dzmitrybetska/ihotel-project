<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.06.2023
  Time: 12:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>login</th>
        <th>password</th>
    </tr>
    </thead>
    <tbody>
    <form action="/user/authorization" method="post">
        <tr>
            <td><input type="text" name="login" placeholder="put login"></td>
            <td><input type="text" name="password" placeholder="put password"></td>
            <td><input type="submit" value="SEND"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
