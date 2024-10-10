<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
    <%
        response.sendRedirect(request.getContextPath() + "/waiting");
    %>
    <a href="auth/login">Login Page</a>
</body>
</html>