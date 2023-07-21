<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.06.2023
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Room update</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>id</th>
    <th>new number</th>
    <th>new price</th>
    <th>new category</th>
    <th>new status</th>
  </tr>
  </thead>

  <tbody>
  <form action="/room/update" method="post">
    <tr>
      <td><input type="text" name="id" placeholder="enter room id to update"></td>
      <td><input type="text" name="number" placeholder="put new number"></td>
      <td><input type="text" name="price" placeholder="new price"></td>
      <td><input type="text" name="roomCategory" placeholder="put new category"></td>
      <td><input type="text" name="roomStatus" placeholder="new status"></td>
      <td><input type="submit" value="UPDATE"></td>
    </tr>
  </form>
  </tbody>
</table>
</body>
</html>
