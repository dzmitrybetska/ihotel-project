<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2023
  Time: 13:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete room</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>id number</th>
    </tr>
    </thead>
    <tbody>
    <form action="/room/delete" method="post">
        <tr>
            <td><input type="text" name="id" placeholder="put id number"></td>
            <td><input type="submit" value="DELETE"></td>
        </tr>
    </form>
    </tbody>
</table>
</body>
</html>
