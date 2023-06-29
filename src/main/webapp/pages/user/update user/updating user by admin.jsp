<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2023
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User update</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>new name</th>
        <th>new surname</th>
        <th>new login</th>
        <th>new password</th>
        <th>new passport series</th>
        <th>new passport ID</th>
        <th>new country</th>
        <th>new email</th>
        <th>new phone</th>
        <th>new role</th>
    </tr>
    </thead>

    <tbody>
    <form action="/user/update" method="post">
        <tr>
            <td><input type="text" name="id" placeholder="put id user"></td>
            <td><input type="text" name="name" placeholder="put new name"></td>
            <td><input type="text" name="surname" placeholder="put new surname"></td>
            <td><input type="text" name="login" placeholder="put new login"></td>
            <td><input type="text" name="password" placeholder="put new password"></td>
            <td><input type="text" name="passportSeries" placeholder="put new passport series"></td>
            <td><input type="text" name="passportID" placeholder="put new passport ID"></td>
            <td><input type="text" name="country" placeholder="put new country"></td>
            <td><input type="text" name="email" placeholder="put new email"></td>
            <td><input type="text" name="phone" placeholder="put new phone"></td>
            <td><input type="text" name="userRole" placeholder="put new role"></td>
            <td><input type="submit" value="UPDATE"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
