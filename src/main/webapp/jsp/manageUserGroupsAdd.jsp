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

<c:choose>
    <c:when test="${add=='complete'}">
        <div class="alert alert-success alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Success!</strong> The record was successfully added to the database.
        </div>
    </c:when>
    <c:when test="${add=='failed'}">
        <div class="alert alert-danger alert-dismissible">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Fail!</strong> The user add to database was unsuccessful.
        </div>
    </c:when>
</c:choose>

<form action="/AddUserGroup" method="post">
<table class="table table-borderless">
    <tr>
        <td></td>
        <td style="width: 30%">
            <div class="form-group">
                <label for="groupName">Group name:</label>
                <input type="text" class="form-control" id="groupName" name="groupName">k
            </div>
        </td>
        <td></td>
    </tr>
    <tr>
        <td></td>
        <td>
            <button type="submit" class="btn btn-primary">Submit</button>
        </td>
        <td></td>
    </tr>
</table>
</form>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
