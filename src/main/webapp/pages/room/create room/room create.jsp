<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 29.06.2023
  Time: 04:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new room</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>room number</th>
    <th>price</th>
    <th>room type (standart, superior, delux, suite)</th>
    <th>room status (serviced, repaired)</th>
  </tr>
  </thead>

  <tbody>
  <form action="/room/create" method="post">
    <tr>
      <td><input type="text" name="number" placeholder="put room number"></td>
      <td><input type="text" name="price" placeholder="put price of room"></td>
      <td><input type="text" name="roomCategories" placeholder="put room type"></td>
      <td><input type="text" name="status" placeholder="put status"></td>
      <td><input type="submit" value="CREATE"></td>
    </tr>
  </form>
  </tbody>
</table>
</body>
</html>
