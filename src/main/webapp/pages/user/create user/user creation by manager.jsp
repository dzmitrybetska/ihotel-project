<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.06.2023
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Guest creation</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>name</th>
    <th>surname</th>
    <th>passport series</th>
    <th>passport ID</th>
    <th>country</th>
    <th>email</th>
    <th>phone</th>
  </tr>
  </thead>
  <tbody>
  <form action="/user/create" method="post">
    <tr>
      <td><input type="text" name="name" placeholder="put name"></td>
      <td><input type="text" name="surname" placeholder="put surname"></td>
      <td><input type="text" name="passportSeries" placeholder="put passport series"></td>
      <td><input type="text" name="passportID" placeholder="put passport ID"></td>
      <td><input type="text" name="country" placeholder="put country"></td>
      <td><input type="text" name="email" placeholder="put email"></td>
      <td><input type="text" name="phone" placeholder="put phone"></td>
      <td><input type="submit" value="CREATE"></td>
    </tr>
  </form>
  </tbody>
</table>
</body>
</html>
