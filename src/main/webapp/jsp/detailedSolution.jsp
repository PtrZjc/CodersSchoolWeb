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
    <title>Detailed solution</title>
    <%@ include file="includes/bootstrap.jspf" %>
</head>
<body>


    <%@ include file="includes/header.jspf" %>

    <table class="table table-striped">
        <tr>
            <th>User</th>
            <th>Exercise title</th>
            <th>Created</th>
            <th>Updated</th>
        </tr>
        <tr>
            <td>${sessionScope.userDetailedSolution.user.username}</td>
            <td>${sessionScope.userDetailedSolution.exercise.title}</td>
            <td>${sessionScope.userDetailedSolution.created}</td>
            <td>${sessionScope.userDetailedSolution.updated}</td>
        </tr>
        <tr>
            <td>Description:</td>
            <td colspan="3" class="text-center" >${sessionScope.userDetailedSolution.description}</td>
        </tr>
    </table>

    <%@ include file="includes/footer.jspf" %>
</div>
</body>
</html>
