<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Recent solutions</title>
    <%@ include file="includes/bootstrap.jspf" %>
</head>
<body>
<%@ include file="includes/header.jspf" %>
<%@ include file="includes/adminHeader.jspf" %>

<%--tOdO check if removed--%>

<c:choose>
    <c:when test="${delete=='complete'}">
        <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Success!</strong> The record was successfully removed from the database.
        </div>
    </c:when>
    <c:when test="${delete=='failed'}">
        <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Fail!</strong> A group with assigned users can not be deleted.
        </div>
    </c:when>
    <c:when test="${update=='complete'}">
        <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Success!</strong> The record was successfully updated.
        </div>
    </c:when>
</c:choose>

<table class="table table-striped border border-0">
    <tr>
        <th class="text-center">Exercise ID</th>
        <th>Title</th>
        <th>Description</th>
        <th class="text-center" colspan="2">Action</th>
    </tr>

    <c:forEach items="${exercises}" var="us">
        <tr>
            <td class="text-center">
                    ${us.id}
            </td>
            <td>
                    ${us.title}
            </td>
            <td>
                    ${us.description}
            </td>
            <td class="text-center">
                <a href='<c:url value="/ManageExercises?&id=${us.id}&action=modify"/>'>
                    Modify
                </a>
            </td>
            <td class="text-center">
                <a href='<c:url value="/ManageExercises?&id=${us.id}&action=delete"/>'>
                    Delete
                </a>
            </td>
        </tr>
    </c:forEach>

</table>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
