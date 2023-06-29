<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 16.06.2023
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting a user</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>id</th>
  </tr>
  </thead>

  <tbody>
  <form action="/user/delete" method="post">
    <tr>
      <td><input type="text" name="id" placeholder="put id"></td>
      <td><input type="submit" value="DELETE"></td>
    </tr>
  </form>
  </tbody>
  </table>
  </body>
</html>
