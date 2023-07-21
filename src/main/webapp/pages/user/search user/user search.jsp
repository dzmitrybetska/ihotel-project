<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2023
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Guest search</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>name</th>
    <th>surname</th>
  </tr>
  </thead>
  <tbody>
  <form action="/user/find" method="post">
    <tr>
      <td><input type="text" name="name" placeholder="put name"></td>
      <td><input type="text" name="surname" placeholder="put surname"></td>
      <td><input type="submit" value="Search"></td>
    </tr>
  </form>
  </tbody>
</table>
</body>
</html>
