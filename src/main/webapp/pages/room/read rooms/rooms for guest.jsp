<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 17.06.2023
  Time: 22:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Rooms</title>
  <a href="/index.jsp" class="c">Go to home page</a>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>number</th>
    <th>price</th>
    <th>room type</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="room" items="${Rooms}">
    <tr>
      <td>${room.number}</td>
      <td>${room.price}</td>
      <td>${room.roomCategories}</td>
    </tr>
  </c:forEach>
  </tbody>
</table>
<form action="/user/account" method="post">
  <td><input type="submit" value="My account"></td>
</form>
</body>
</html>
