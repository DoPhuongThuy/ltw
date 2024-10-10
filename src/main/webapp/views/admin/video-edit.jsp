<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Video</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <h1>Edit Video</h1>

    <form action="${pageContext.request.contextPath}/admin/video/edit" method="post" enctype="multipart/form-data">
        <input type="hidden" name="videoId" value="${video.videoId}"/>

        <table>
            <tr>
                <td>Title:</td>
                <td><input type="text" name="title" value="${video.title}" required></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description" rows="5" cols="30">${video.description}</textarea></td>
            </tr>
            <tr>
                <td>Poster:</td>
                <td>
                    <img src="${pageContext.request.contextPath}/uploads/${video.poster}" width="100" alt="Current Poster"/><br/>
                    <input type="file" name="poster" accept="image/*">
                    <input type="hidden" name="imageLink" value="${video.poster}">
                </td>
            </tr>
            <tr>
                <td>Category:</td>
                <td>
                    <select name="categoryId">
                        <c:forEach var="category" items="${categoryList}">
                            <option value="${category.categoryId}" ${category.categoryId == video.category.categoryId ? 'selected' : ''}>
                                ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Active:</td>
                <td>
                    <input type="radio" name="active" value="1" ${video.active == 1 ? 'checked' : ''}> Active
                    <input type="radio" name="active" value="0" ${video.active == 0 ? 'checked' : ''}> Inactive
                </td>
            </tr>
            <tr>
                <td>Views:</td>
                <td><input type="number" name="views" value="${video.views}" required></td>
            </tr>
            <tr>
                <td></td>
                <td>
                    <button type="submit">Update Video</button>
                    <a href="${pageContext.request.contextPath}/admin/videos">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
