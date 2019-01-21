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
    <c:forEach items="${sessionScope.userGroups}" var="ug">
        <tr>
            <td>${ug.id}</td>
            <td>${ug.name}</td>
            <td><a href='<c:url value="/ShowUsersFromGroup?&id="/>${ug.id}'>Show users</a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
