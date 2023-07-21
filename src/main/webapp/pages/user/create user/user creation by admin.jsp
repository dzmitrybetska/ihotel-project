<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 28.06.2023
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User creation</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>login (only latin letters, numbers and underscores, length must be between 5 and 20 characters)</th>
        <th>password (only Latin letters, numbers and underscores, at least 8 characters)</th>
        <th>passport series</th>
        <th>passport ID</th>
        <th>country</th>
        <th>email</th>
        <th>phone</th>
        <th>role</th>
    </tr>
    </thead>
    <tbody>
    <form action="/user/create" method="post">
        <tr>
            <td><input type="text" name="name" placeholder="put name"></td>
            <td><input type="text" name="surname" placeholder="put surname"></td>
            <td><input type="text" name="login" placeholder="put login"></td>
            <td><input type="text" name="password" placeholder="put password"></td>
            <td><input type="text" name="passportSeries" placeholder="put passport series"></td>
            <td><input type="text" name="passportID" placeholder="put passport ID"></td>
            <td><input type="text" name="country" placeholder="put country"></td>
            <td><input type="text" name="email" placeholder="put email"></td>
            <td><input type="text" name="phone" placeholder="put phone"></td>
            <td><input type="text" name="userRole" placeholder="put role"></td>
            <td><input type="submit" value="CREATE"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
