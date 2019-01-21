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
        <th>User ID</th>
        <th>Username</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${sessionScope.users}" var="us">
        <tr>
            <td>${us.id}</td>
            <td>${us.username}</td>
            <td><a href='<c:url value="/UserDetails?&id="/>${us.id}'>Show details</a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
