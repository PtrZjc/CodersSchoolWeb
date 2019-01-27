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
        <th>User group ID</th>
        <th>User group name</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${userGroups}" var="us">
        <tr>
            <td>${us.id}</td>
            <td>${us.name}</td>
            <td><a href='<c:url value="/ShowUsersFromGroup?&id="/>${us.id}'>Show users</a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
