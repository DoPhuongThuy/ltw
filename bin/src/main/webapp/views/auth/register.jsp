<%--&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: benpham--%>
<%--  Date: 16/09/2024--%>
<%--  Time: 16.06--%>
<%--  To change this template use File | Settings | File Templates.--%>

<html>
<head>
    <title>HTML Login Form</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/views/auth/register.css">
</head>
<body>
    <div class="main">
        <h3>Have you already had an account?</h3>
        <form action="register" method="POST">
            <label for="username">
            </label>
            <input
                    class="input-custom"
                    type="text"
                    id="username"
                    name="username"
                    placeholder="username" required>

            <label for="fullName">
            </label>
            <input
                    class="input-custom"
                    type="text"
                    id="fullName"
                    name="fullName"
                    placeholder="full name" required>

            <label for="email">
            </label>
            <input
                    class="input-custom"
                    type="text"
                    id="email"
                    name="email"
                    placeholder="email" required>

            <label for="phone">
            </label>
            <input
                    class="input-custom"
                    type="text"
                    id="phone"
                    name="phone"
                    placeholder="phone number" required>

            <label for="password">
            </label>
            <input class="input-custom"
                   type="password"
                   id="password"
                   name="password"
                   placeholder="password" required>

            <label for="re-password">
            </label>
            <input class="input-custom"
                   type="password"
                   id="re-password"
                   name="re-password"
                   placeholder="Re-enter your password" required>

            <div class="checkbox-group">
                <input type="checkbox" id="terms" name="terms" required>
                <label for="terms">I agree to all statements in
                    <a href="terms-of-service.html">Terms of Service</a>
                </label>
            </div>

            <div class="wrap">
                <button type="submit"
                        onclick="solve()">
                    Register
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
        <p>Already have an account?
            <a href="login">
                Log in
            </a>
        </p>
    </div>
</body>
</html>