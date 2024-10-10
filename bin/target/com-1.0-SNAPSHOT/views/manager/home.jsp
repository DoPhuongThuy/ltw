<%--
  Created by IntelliJ IDEA.
  User: benpham
  Date: 19/09/2024
  Time: 14.52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<html>
<head>
    <title>Manager's Home Page</title>
    <style>
        .homepage {
            display: flex;
            justify-content: center;
            align-items: center;
        }
    </style>
</head>
<body>
    <c:choose>
        <c:when test="${sessionScope.account == null}">
            <div class="homepage">
                <h1>You haven't login, please login or register</h1>
            </div>
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath }/auth/login">Login</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/auth/register">Register</a>
                    </li>
                    <li>
                        <i class="search fa fa-search search-button"></i>
                    </li>
                </ul>
            </div>
        </c:when>
        <c:when test="${sessionScope.account.roleId != 2}">
            <div class="homepage">
                <h1>You doesn't have manager role</h1>
            </div>
        </c:when>
        <c:otherwise>
            <div class="homepage">
                <h1>Manager's Home Page</h1>
            </div>
            <div class="col-sm-6">
                <ul class="list-inline right-topbar pull-right">
                    <li>
                        <a href="${pageContext.request.contextPath}/member/my-account">My
                            account: ${sessionScope.account.fullName}</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath }/logout">Logout</a>
                    </li>
                </ul>
            </div>
        </c:otherwise></c:choose>
</body>
</html>

