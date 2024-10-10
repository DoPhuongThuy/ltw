<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Video List</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        /* Thêm một chút CSS để cải thiện giao diện */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f2f2f2;
        }
        img {
            cursor: pointer; /* Thay đổi con trỏ khi hover qua hình ảnh */
        }
    </style>
    <script>
        function openFullscreen(videoElement) {
            if (videoElement.requestFullscreen) {
                videoElement.requestFullscreen();
            } else if (videoElement.mozRequestFullScreen) {
                videoElement.mozRequestFullScreen();
            } else if (videoElement.webkitRequestFullscreen) {
                videoElement.webkitRequestFullscreen();
            } else if (videoElement.msRequestFullscreen) {
                videoElement.msRequestFullscreen();
            }
        }
    </script>
</head>
<body>
    <h1>Video List</h1>

    <table>
        <thead>
            <tr>
                <th>Video ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Poster</th>
                <th>Category</th>
                <th>Views</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="video" items="${videoList}">
                <tr>
                    <td>${video.videoId}</td>
                    <td>${video.title}</td>
                    <td>${video.description}</td>
                    <td>
                        <img src="${pageContext.request.contextPath}/uploads/${video.poster}" alt="Poster" height="100" 
                             onclick="openFullscreen(this)">
                    </td>
                    <td>${video.category.categoryName}</td>
                    <td>${video.views}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/admin/video/edit?id=${video.videoId}">Edit</a> |
                        <a href="${pageContext.request.contextPath}/admin/video/delete?id=${video.videoId}" onclick="return confirm('Are you sure you want to delete this video?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <a href="${pageContext.request.contextPath}/admin/video/add">Add New Video</a>
</body>
</html>
