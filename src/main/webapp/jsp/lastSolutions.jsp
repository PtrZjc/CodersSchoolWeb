<%--
  Created by IntelliJ IDEA.
  User: goddy
  Date: 19.01.19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Index</title>
</head>
<body>
<%@ include file="header.jspf" %>

<table border="1px">
    <tr>
        <th>User</th>
        <th>Exercise title</th>
        <th>Created</th>
        <th>Contents</th>
    </tr>
    <c:forEach items="${sessionScope.solutions}" var="sol">
        <tr>
            <td>${sol.user.username}</td>
            <td>${sol.exercise.title}</td>
            <td>${sol.created}</td>
            <td><a href='<c:url value="/SolutionDetails?id="/>${sol.id}'>Details</a>
            </td>
        </tr>
    </c:forEach>
</table>

<%@ include file="footer.jspf" %>
</body>
</html>
