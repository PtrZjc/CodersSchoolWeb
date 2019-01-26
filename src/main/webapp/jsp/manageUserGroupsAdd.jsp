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

<form action="/AddUserGroup" method="post">
<table class="table table-borderless">
    <tr>
        <td></td>
        <td style="width: 30%">
            <div class="form-group">
                <label for="groupName">Group name:</label>
                <input type="text" class="form-control" id="groupName" name="groupName">
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
