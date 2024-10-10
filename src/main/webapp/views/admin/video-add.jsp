<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Video</title>
</head>
<body>
<h1>Add Video</h1>
<form action="${pageContext.request.contextPath}/admin/video/insert" method="post" enctype="multipart/form-data">
    <label>Title:</label>
    <input type="text" name="title" required /><br />
    <label>Active:</label>
    <input type="number" name="active" required /><br />
    <label>Views:</label>
    <input type="number" name="views" required /><br />
    <label>Description:</label>
    <textarea name="description"></textarea><br />
    <label>Poster:</label>
    <input type="file" name="poster" required /><br />
    <label>Video File:</label>
    <input type="file" name="videoFile" required /><br />
    <input type="submit" value="Add Video" />
</form>
<a href="${pageContext.request.contextPath}/admin/videos">Back to Video List</a>
</body>
</html>
