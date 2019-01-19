<%--
  Created by IntelliJ IDEA.
  User: goddy
  Date: 19.01.19
  Time: 16:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Index</title>
</head>
<body>

<%--<jsp:include page="header.jspf"/>--%>

<table border="1px">
    <tr>
        <td>User ID</td>
        <td>Created</td>
        <td>Solution description</td>
    </tr>
    <c:forEach items="${solutions}" var="sol">
        <tr>
            <td>${sol.id}</td>
            <td>${sol.created}</td>
            <td>${sol.description}</td>
        </tr>
    </c:forEach>
</table>

<jsp:include page="footer.jspf"/>

</body>
</html>
