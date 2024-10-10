<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 16/09/2024
  Time: 16.06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="core" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<html>
<head>
    <title>Forgot password</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/auth/forgot-password.css">
</head>
<body>
    <div class=" main
    ">
        <h1>Forgotten password</h1>
        <form action="forgot-password" method="POST">
            <label for="email">
            </label>
            <input class="input-custom" type="text"
                   id="email"
                   name="email"
                   placeholder="Email" required>

            <label for="password">
            </label>
            <input class="input-custom" type="password"
                   id="password"
                   name="password"
                   placeholder="Password" required>

            <label for="password">
            </label>
            <input class="input-custom" type="password"
                   id="re-password"
                   name="re-password"
                   placeholder="Re-enter password" required>
            <div class="wrap">
                <button type="submit"
                        onclick="solve()">
                    Submit
                </button>
            </div>
        </form>
        <h5 style="color: red">
            <%
                Object alert = request.getAttribute("alert");
                if (alert != null) {
                    out.print(alert);
                }
            %>
        </h5>
    </div>
</body>
</html>

