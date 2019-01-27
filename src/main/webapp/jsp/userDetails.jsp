<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User details</title>
    <%@ include file="includes/bootstrap.jspf" %>
</head>
<body>
<%@ include file="includes/header.jspf" %>

<table class="table table-striped border border-0">
    <tr>
        <th>User ID</th>
        <th>Username</th>
        <th>Mail</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.username}</td>
        <td>${user.email}</td>
        </td>
    </tr>
</table>

<div class="py-3 px-5 bg-light text-left"><h3>Exercises solved by user:</h3></div>
<table class="table table-striped border border-0">
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${exercises}" var="us">
        <tr>
            <td>${us.id}</td>
            <td>${us.title}</td>
            <td><a href='<c:url value="/ShowSolutionDetails?&id="/>${us.id}'>Solution details</a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
