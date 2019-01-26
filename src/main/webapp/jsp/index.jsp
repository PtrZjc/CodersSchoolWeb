<%--
  Created by IntelliJ IDEA.
  User: goddy
  Date: 26.01.19
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    int[] cyferki = new int[10];
    for (int i = 0; i < 10; i++) {
        cyferki[i] = i * i;
}%>

Tu jest jakikolwiek text

<% for (int cyfra: cyferki) { %>
  <%=cyfra%><br>
<%}%>
</body>
</html>
