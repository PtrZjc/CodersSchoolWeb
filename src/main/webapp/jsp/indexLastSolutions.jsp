<%--
  Created by IntelliJ IDEA.
  User: goddy
  Date: 19.01.19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Recent solutions</title>
    <%@ include file="includes/bootstrap.jspf" %>
</head>
<body>
<%@ include file="includes/header.jspf" %>

<table class="table table-striped border border-0">
    <tr>
        <th>User</th>
        <th>Exercise title</th>
        <th>Created</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${solutions}" var="sol">
        <tr>
            <td>${sol.user.username}</td>
            <td>${sol.exercise.title}</td>
            <td>${sol.created}</td>
            <td><a href='<c:url value="/ShowSolutionDetails?&id="/>${sol.id}'>More</a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
