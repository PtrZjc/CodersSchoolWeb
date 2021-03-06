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
            <strong>Fail!</strong> Incorrect input data.
        </div>
    </c:when>
</c:choose>

<form action="/AddUser" method="post">
    <div class="row">
        <div class="col"></div>
        <div class="col"><h4>Add an user to database.</h4></div>
        <div class="col"></div>
    </div>
    <div class="row">
        <div class="col"></div>

        <div class="col">
            <div class="form-group">
                <label for="username">User name:</label>
                <input type="text" class="form-control" id="username" name="username">
            </div>
        </div>
        <div class="col"></div>

    </div>

    <div class="row">
        <div class="col"></div>
        <div class="col">
            <div class="form-group">
                <label for="email">User email:</label>
                <input type="email" class="form-control" id="email" name="email">
            </div>
        </div>
        <div class="col"></div>

    </div>

    <div class="row">
        <div class="col"></div>
        <div class="col">
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
        </div>
        <div class="col"></div>

    </div>

    <div class="row">
        <div class="col"></div>
        <div class="col">
            <div class="form-group">
                <label for="sel1">Assign user to group:</label>
                <select name="userGroupID" class="form-control" id="sel1">
                    <c:forEach items="${userGroups}" var="ug">
                        <option value="${ug.id}">${ug.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col"></div>

    </div>




    <div class="row">
        <div class="col"></div>
        <div class="col">
            <button type="submit" class="btn btn-primary">Submit</button>
        </div>
        <div class="col"></div>
    </div>
</form>


<%@ include file="includes/footer.jspf" %>

</body>
</html>
