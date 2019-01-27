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

<form action="/AddExercise" method="post">
    <div class="row">
        <div class="col"></div>
        <div class="col"><h4>Add an exercise to database.</h4></div>
        <div class="col"></div>
    </div>
    <div class="row">
        <div class="col"></div>

        <div class="col">
            <div class="form-group">
                <label for="title">Exercise title:</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
        </div>
        <div class="col"></div>

    </div>

    <div class="row">
        <div class="col"></div>
        <div class="col">
            <div class="form-group">
                <label for="description">Exercise description:</label>
                <textarea class="form-control" rows="5" id="description" name="description"></textarea>
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
