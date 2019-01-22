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

<div class="alert alert-success alert-dismissible">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong> The record was successfully removed from the database.
</div>

<table class="table table-striped border border-0">
    <tr>
        <th class="text-center">User group ID</th>
        <th>User group name</th>
        <th class="text-center" colspan="2">Action</th>
    </tr>
    <c:forEach items="${userGroups}" var="ug">
        <tr>
            <td class="text-center" style="width: 20%">${ug.id}</td>
            <td>${ug.name}</td>
            <td class="text-center"><a href='<c:url value="/ShowUsersFromGroup?&id="/>${ug.id}'>Modify</a></td>
            <td class="text-center"><a href='<c:url value="/ShowUsersFromGroup?&id="/>${ug.id}' data-toggle="modal"
                                       data-target="#confirmDeleteModal">Delete</a></td>
        </tr>
    </c:forEach>

</table>

<!-- Modal -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header text-center">
                    <h5 class="text-center" id="confirmDeleteModalLabel">Are you sure?</h5>
            </div>
            <div class="modal-body text-center">
                All users in the group will be also deleted.
            </div>
            <div class="modal-footer">
                <form action="" method="post">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="includes/footer.jspf" %>

</body>
</html>
